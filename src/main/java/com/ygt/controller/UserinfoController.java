package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Userinfo;
import com.ygt.service.UserinfoService;
import com.ygt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
public class UserinfoController {

    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private MD5Util md5Util;

    //用户注册
    @RequestMapping(value="enrollduser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrolluser(String username,String userphone,String userpassword) throws NoSuchAlgorithmException {
        Userinfo finduser = userinfoService.finduser(userphone);
        JSONObject obj = new JSONObject();
        if(finduser==null){
            Integer userid = null;
            Integer useridentity = 1;
            userinfoService.enrolluser(userid,username, userphone, md5Util.md5(new String (userpassword)),useridentity);
            obj.put("200","成功");
            return obj.toString();
        }else{
            obj.put("400","失败");
            return obj.toString();
        }
    }
    //账号登录
    @RequestMapping(value="loginuser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loginuser(String userphone, String userpassword, HttpSession session) throws NoSuchAlgorithmException {
        Userinfo loginuser = userinfoService.loginuser(userphone, md5Util.md5(new String (userpassword)));
        JSONObject obj = new JSONObject();
        if(loginuser!=null){
            Userinfo finduser = userinfoService.finduser(userphone);
            Integer userid = finduser.getUserid();
            session.setAttribute("userid",userid);
            obj.put("200","成功");
            return obj.toString();
        }else{
            obj.put("400","失败");
            return obj.toString();
        }
    }
    //修改密码
    @RequestMapping(value="changeuser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String changeuser(String userphone,String userpassword) throws NoSuchAlgorithmException {
        Userinfo loginuser = userinfoService.loginuser(userphone, md5Util.md5(new String(userpassword)));
        JSONObject obj = new JSONObject();
        if(loginuser==null){
            userinfoService.changeuser(userphone,  md5Util.md5(new String (userpassword)));
            obj.put("200","成功");
            return obj.toString();
        }else{
            obj.put("400","失败");
            return obj.toString();
        }
    }
}
