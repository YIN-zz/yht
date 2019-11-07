package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import com.ygt.service.SendCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@Controller
public class SendCodeController {

    @Autowired
    private SendCodeService sendCodeService;

    //获取手机验证码
    @RequestMapping(value="getsendCode",produces="application/json; charset=utf-8")
    @ResponseBody
    public String getsendCode(String userphone, HttpSession session) throws IOException, NumberParseException {
        //response.setHeader("Access-Control-Allow-Origin", "*");
        String swissNumberStr =userphone;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        //解析手机号码
        Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(swissNumberStr, "CN"); // ①
        PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance(); // ②
        boolean isValid = phoneUtil.isValidNumber(phoneNumber); // returns true
        JSONObject obj = new JSONObject();
        if(isValid){
            String codes = sendCodeService.sendCodes(userphone);
            session.setAttribute("codes",codes);
            System.out.println(codes);
            session.setMaxInactiveInterval(60*60);//session保存验证码时间10分钟
            obj.put("200","成功");
            return obj.toString();
        }else{
            obj.put("400","失败");
            return obj.toString();
        }
    }

    //验证码进行验证
    @RequestMapping(value="verification",produces="application/json; charset=utf-8")
    @ResponseBody
    public String verification(String usersecurity,HttpSession session) throws IOException {
        JSONObject obj = new JSONObject();
        if(usersecurity.equals(session.getAttribute("codes"))){
            obj.put("200","成功");
            return obj.toString();
        }else{
            obj.put("400","失败");
            return obj.toString();
        }
    }
}