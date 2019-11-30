package com.ygt.controller;

import com.ygt.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/easytubepass")
public class SmsController {
    @Autowired
    private SmsService smsService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //获取验证码
    @RequestMapping(value="getsendCodes",produces="application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> getSendCodes(String userphone,HttpSession session) throws ClientException {
        String codes = smsService.sendSms(userphone);
        session.setAttribute("codes",codes);
        session.setMaxInactiveInterval(60*2);//session保存验证码时间10分钟
        if(codes!=null){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }
}
