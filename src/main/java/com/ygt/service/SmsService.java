package com.ygt.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class SmsService {
    /* // 产品名称:云通信短信API产品,开发者无需替换
     static final String product = "Dysmsapi";
     // 产品域名,开发者无需替换
     static final String domain = "dysmsapi.aliyuncs.com";

     // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
     static final String accessKeyId = "LTAI4FfwwwJ1Vd576kcbcg24";           // TODO 改这里
     static final String accessKeySecret = "gE81Eq8f7V6jdjFfLtjYoURbF7R08K"; // TODO 改这里



     public static SendSmsResponse sendSms(String userphone) throws ClientException {

         // 可自助调整超时时间
         System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
         System.setProperty("sun.net.client.defaultReadTimeout", "10000");

         // 初始化acsClient,暂不支持region化
         IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
         DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
         IAcsClient acsClient = new DefaultAcsClient(profile);

         // 组装请求对象-具体描述见控制台-文档部分内容
         SendSmsRequest request = new SendSmsRequest();
         // 必填:待发送手机号
         request.setPhoneNumbers(userphone);
         // 必填:短信签名-可在短信控制台中找到
         request.setSignName("易管通可追溯系统"); // TODO 改这里
         //生成6位数字代码
         String code = "";
         int [] numbers = {0,1,2,3,4,5,6,7,8,9};
         Random random = new Random();
         for (int i = 0; i < 6; i++) {
             //目的是产生足够随机的数，避免产生的数字重复率高的问题
             int next = random.nextInt(10000);
             code+=numbers[next%10];
         }
         // 必填:短信模板-可在短信控制台中找到
         request.setTemplateCode("您的验证码：${code}，您正进行身份验证，打死不告诉别人！");  // TODO 改这里
         // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
         request.setTemplateParam("{\"code\":\"" + code + "\"}");

         // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
         // request.setSmsUpExtendCode("90997");

         // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
         request.setOutId("yourOutId");

         // hint 此处可能会抛出异常，注意catch
         SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
         if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
             System.out.println("短信发送成功！");
         }else {
             System.out.println("短信发送失败！");
         }
         return sendSmsResponse;
     }

     //以下为测试代码，随机生成验证码
     private static int newcode;
     public static int getNewcode() {
         return newcode;
     }
     public static void setNewcode(){
         String newcode = "";
         int [] numbers = {0,1,2,3,4,5,6,7,8,9};
         Random random = new Random();
         for (int i = 0; i < 6; i++) {
             //目的是产生足够随机的数，避免产生的数字重复率高的问题
             int next = random.nextInt(10000);
             newcode+=numbers[next%10];
         }
         System.out.println(newcode);
     //    newcode = (int)(Math.random()*9999)+100;  //每次调用生成一次四位数的随机数
     }*/
  /*  public static void main(String[] args) throws Exception {
        setNewcode();
        String code = Integer.toString(getNewcode());
        SendSmsResponse sendSms =sendSms("15315633143",code);//填写你需要测试的手机号码
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + sendSms.getCode());
        System.out.println("Message=" + sendSms.getMessage());
        System.out.println("RequestId=" + sendSms.getRequestId());
        System.out.println("BizId=" + sendSms.getBizId());
    }*/
    //对应你阿里云账户的 accessKeyId
    static final String accessKeyId = "LTAI4FfwwwJ1Vd576kcbcg24";
    //对应你阿里云账户的 accessKeySecret
    static final String accessKeySecret = "gE81Eq8f7V6jdjFfLtjYoURbF7R08K";
    //对应签名名称
    private static final String signName = "易管通可追溯系统";
    //对应模板代码
    private static final String templateCode = "您的验证码：${code}，您正进行身份验证，打死不告诉别人！";
    //验证码
    private static int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);

    /**
     * 短信发送
     *
     * @param userphone 发送的手机号
     */
    public Integer SendCode(String userphone) {

        DefaultProfile profile = DefaultProfile.getProfile("default",
                accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        //阿里云对应发送短信的服务器地址
        request.setDomain("dysmsapi.aliyuncs.com");
        //对应的版本号
        request.setVersion("2017-05-25");

        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", userphone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":" + mobile_code + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return mobile_code;
    }
}

