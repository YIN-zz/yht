package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/SmsController")
public class SmsController {
    @Autowired
    private SmsService smsService;

    //获取验证码
    @RequestMapping(value="getsendCodes",produces="application/json; charset=utf-8")
    @ResponseBody
    public String getSendCodes(String userphone,HttpSession session) throws ClientException {
        String codes = smsService.sendSms(userphone);
        //int codes = Integer.parseInt(s);
        session.setAttribute("codes",codes);
        session.setMaxInactiveInterval(60*2);//session保存验证码时间10分钟
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        return obj.toString();
    }
}
