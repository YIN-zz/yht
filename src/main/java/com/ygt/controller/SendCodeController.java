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
        JSONObject obj = new JSONObject();
        if(userphone!=null){
            String code = sendCodeService.sendCodes(userphone);
            Integer codes = Integer.parseInt(code);
            session.setAttribute("codes",codes);
            session.setMaxInactiveInterval(60*2);//session保存验证码时间2分钟
            obj.put("msg","成功");
            return obj.toString();
        }else{
            obj.put("msg","失败");
            return obj.toString();
        }
    }

    //验证码进行验证
    @RequestMapping(value="verification",produces="application/json; charset=utf-8")
    @ResponseBody
    public String verification(String usersecurity,HttpSession session) throws IOException {
        JSONObject obj = new JSONObject();
        if(usersecurity.equals(session.getAttribute("codes"))){
            obj.put("msg","成功");
            return obj.toString();
        }else{
            obj.put("msg","失败");
            return obj.toString();
        }
    }
}