package com.ygt.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

public class PhoneController {

    //根据手机号获取号码归属地
    @RequestMapping("getaddress")
    @ResponseBody
    public String getaddress(String phone) throws NumberParseException {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(phone, "CN"); // ①
        PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance(); // ②
        //geocoder.getDescriptionForNumber(phoneNumber, Locale.ENGLISH);
        return geocoder.getDescriptionForNumber(phoneNumber, Locale.CHINESE);
    }
}
