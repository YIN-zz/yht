package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.service.SendCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class SendCodeController {

    @Autowired
    private SendCodeService sendCodeService;

    //获取手机验证码
    @RequestMapping(value="getsendCode",produces="application/json; charset=utf-8")
    @ResponseBody
    public String getsendCode(String userphone, HttpSession session) throws IOException {
        String codes = sendCodeService.sendCodes(userphone);
        session.setAttribute("codes",codes);
        session.setMaxInactiveInterval(60*10);//session保存验证码时间10分钟
        JSONObject obj = new JSONObject();
        obj.put("200","成功");
        return obj.toString();
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