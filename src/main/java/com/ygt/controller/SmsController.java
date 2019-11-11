package com.ygt.controller;

import com.ygt.service.SmsService;
import com.ygt.util.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
@Controller
public class SmsController {
    @Autowired
    private SmsService smsService;
/*    @RequestMapping("getsendCode")
    public String getsendCode(String userphone,String usersecurity,HttpSession session) throws IOException{
        DefaultProfile profile = DefaultProfile.getProfile("华东1（山东）", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "华东1（山东）");

        request.putQueryParameter("PhoneNumbers", "15315633143");
        request.putQueryParameter("SignName", "易管通可追溯系统");
        request.putQueryParameter("TemplateCode", "SMS_176938968");
        request.putQueryParameter("TemplateParam", "{\"code\":\"123456\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }*/

    //    return "";
 //   }
    //获取验证码
    @RequestMapping("s")
    public String getSendCode(String userphone,HttpSession session){
        Integer integer = smsService.SendCode(userphone);
        session.setAttribute("integer",integer);
        session.setMaxInactiveInterval(60*2);//session保存验证码时间10分钟
        System.out.println(integer);
        return "";
    }
}
