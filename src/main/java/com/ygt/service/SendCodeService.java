package com.ygt.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.ygt.util.CheckSumBuilder;

@Service
public class SendCodeService {

	//发送验证码的请求路径URL
    private static final String
            SERVER_URL="https://api.netease.im/sms/sendcode.action";
	//网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String
            APP_KEY="9803bfaf05f7fe4a98edf20196e1342f";
	//网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET="7f306b36fe0a";
	//随机数
    private static final String NONCE="123456";
	//短信模板ID
    private static final String TEMPLATEID="14845166";
	//手机号								13888888888
    private static final String MOBILE="17606448645";
	//验证码长度，范围4～10，默认为4
    private static final String CODELEN="6";
    
    public String sendCodes(String userphone) throws ClientProtocolException, IOException {
    			
    		DefaultHttpClient httpClient = new DefaultHttpClient();
    	    HttpPost httpPost = new HttpPost(SERVER_URL);
    	    String curTime = String.valueOf((new Date()).getTime() / 1000L);
			/*
			 * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
			 */
    	    String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

		    // 设置请求的header
    	    httpPost.addHeader("AppKey", APP_KEY);
    	    httpPost.addHeader("Nonce", NONCE);
    	    httpPost.addHeader("CurTime", curTime);
    	    httpPost.addHeader("CheckSum", checkSum);
    	    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

    	    // 设置请求的的参数，requestBody参数
    	    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			/*
			 * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
			 * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
			 * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
			 */
    	    nvps.add(new BasicNameValuePair("templateid", TEMPLATEID));
    	        
    	    //改成发往前台传进来的手机号
    	    nvps.add(new BasicNameValuePair("mobile", userphone));
    	    //nvps.add(new BasicNameValuePair("mobile", MOBILE));
    	    nvps.add(new BasicNameValuePair("codeLen", CODELEN));

    	    httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

		    // 执行请求
    	    HttpResponse response1 = httpClient.execute(httpPost);
			/*
			 * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
			 * 2.具体的code有问题的可以参考官网的Code状态表
			 */
    	        
    	    String str = EntityUtils.toString(response1.getEntity(), "utf-8");
    	    System.out.println("接受验证码的手机为"+MOBILE);
    	    System.out.println("发送的验证码"+CODELEN);
    	    System.out.println(str);
			return str;

			//把验证码的数据返回给前台
    	   /* response.setContentType("text/plain");
    	    PrintWriter out = response.getWriter();
    	    out.write(str);
    	    out.flush();
    	    out.close();*/
    }
}