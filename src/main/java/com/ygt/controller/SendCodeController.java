package com.ygt.controller;

import com.ygt.service.SendCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class SendCodeController {

    @Autowired
    private SendCodeService sendCodeService;

    //发送手机验证码
    @RequestMapping("sendCode")
    @ResponseBody
    public String sendCode(String telphone) throws IOException {
        String sendCode = sendCodeService.sendCodes(telphone);
        return sendCode;
    }
}