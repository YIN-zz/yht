package com.ygt.controller;

import com.ygt.service.SendCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/easytubepass")
public class SendCodeController {

    @Autowired
    private SendCodeService sendCodeService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //获取手机验证码
    @RequestMapping(value="getsendCode",produces="application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> getsendCode(String userphone, HttpSession session) throws IOException {
        if(userphone!=null){
            String code = sendCodeService.sendCodes(userphone);
            Integer codes = Integer.parseInt(code);
            session.setAttribute("codes",codes);
            session.setMaxInactiveInterval(60*2);//session保存验证码时间2分钟
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //验证码进行验证（测试用的）
    @RequestMapping(value="verification",produces="application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> verification(String usersecurity,HttpSession session) throws IOException {
        if(usersecurity.equals(session.getAttribute("codes"))){
            result.put("msg","成功");
        }else{
            result.put("msg","失败");
        }
        return result;
    }
}