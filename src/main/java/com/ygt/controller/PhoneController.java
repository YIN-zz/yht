package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.aspire.nm.component.commonUtil.xml.XStreamHelperDemo.PhoneNumber;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@Controller
public class PhoneController {

    //根据手机号获取号码归属地
    @RequestMapping(value="getaddress",produces="application/json; charset=utf-8")
    @ResponseBody
    public String getaddress(String userphone) throws NumberParseException {
        String swissNumberStr =userphone;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        //解析手机号码
        Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(swissNumberStr, "CN"); // ①
        PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance(); // ②
        /*以英文的形式显示号码归属地*/
        //return geocoder.getDescriptionForNumber(phoneNumber, Locale.ENGLISH);
       // return geocoder.getDescriptionForNumber(phoneNumber, Locale.CHINESE);
        /*根据需求输出多种格式的号码格式*/
        // Produces "+41 44 668 18 00"
        //System.out.println(phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL));
        // Produces "044 668 18 00"
        //System.out.println(phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL));
        // Produces "+41446681800"
        //System.out.println(phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164));
        /*检测输入的号码是否有效*/
        boolean isValid = phoneUtil.isValidNumber(phoneNumber); // returns true
        JSONObject obj = new JSONObject();
        if(isValid){
            //手机号码归属地
            String descriptionForNumber = geocoder.getDescriptionForNumber(phoneNumber, Locale.ENGLISH);
            obj.put("200","成功");
            obj.put("descriptionForNumber",descriptionForNumber);
            return obj.toString();
        }else{
            obj.put("400","失败");
            return obj.toString();
        }
    }
}
