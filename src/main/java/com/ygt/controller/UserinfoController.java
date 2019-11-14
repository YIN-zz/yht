package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Userinfo;
import com.ygt.service.UserinfoService;
import com.ygt.util.MD5Util;
import com.ygt.util.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Controller
public class UserinfoController {

    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private MD5Util md5Util;
    @Autowired
    private UploadService uploadService;

    //企管用户注册
    @RequestMapping(value="enrolluser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrolluser(String username,String userphone,String userpassword,String usersecurity,HttpSession session) throws NoSuchAlgorithmException {
        JSONObject obj = new JSONObject();
        String codes = (String)session.getAttribute("codes");
        if(usersecurity.equals(codes)) {
            Userinfo finduser = userinfoService.finduser(userphone);
            if (finduser == null) {
                Integer userid = null;
                Integer useridentity = 2;
                userinfoService.enrolluser(userid, username, userphone, md5Util.md5(new String(userpassword)), useridentity);
                Userinfo finduser1 = userinfoService.finduser(userphone);
                Integer userid1 = finduser1.getUserid();
                session.setAttribute("userid", userid1);
                obj.put("msg", "成功");
                return obj.toString();
            } else{
                obj.put("msg", "该账户已经注册");
                return obj.toString();
            }
        }else {
            obj.put("msg", "验证码输入错误");
            return obj.toString();
        }
    }
    //账号登录
    @RequestMapping(value="loginuser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loginuser(String userphone, String userpassword, HttpSession session) throws NoSuchAlgorithmException {
        Userinfo finduser = userinfoService.finduser(userphone);
        JSONObject obj = new JSONObject();
        if(finduser!=null){
            Userinfo loginuser = userinfoService.loginuser(userphone, md5Util.md5(new String (userpassword)));
            if(loginuser!=null){
                Integer userid = finduser.getUserid();
                session.setAttribute("userid",userid);
                String username = finduser.getUsername();
                String userphoto = finduser.getUserphoto();
                session.setAttribute("userphoto",userphoto);
                Integer useridentity = loginuser.getUseridentity();
                obj.put("登录人名字",username);
                obj.put("message",useridentity);
                obj.put("msg","成功");
                return obj.toString();
            }else{
                obj.put("msg","密码输入错误");
                return obj.toString();
            }
        }else {
            obj.put("msg","该账户不存在");
            return obj.toString();
        }

    }
    //手机验证码登录
    @RequestMapping(value="enteruser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enteruser(String userphone,String usersecurity,HttpSession session){
        JSONObject obj = new JSONObject();
        String codes = (String)session.getAttribute("codes");
        if(usersecurity.equals(codes)){
            Userinfo finduser = userinfoService.finduser(userphone);
            Integer userid = finduser.getUserid();
            session.setAttribute("userid",userid);
            String username = finduser.getUsername();
            String userphoto = finduser.getUserphoto();
            session.setAttribute("userphoto",userphoto);
            Integer useridentity = finduser.getUseridentity();
            obj.put("登录人名字",username);
            obj.put("message",useridentity);
            obj.put("msg","成功");
            return obj.toString();
        }else{
            obj.put("msg","失败");
            return obj.toString();
        }
    }
    //修改密码
    @RequestMapping(value="changeuser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String changeuser(HttpSession session,String userpassword) throws NoSuchAlgorithmException {
        JSONObject obj = new JSONObject();
        Integer userid = (Integer)session.getAttribute("userid");
        Userinfo findusername = userinfoService.findusername(userid);
        String userphone = findusername.getUserphone();
        userinfoService.changeuser(userphone,  md5Util.md5(new String (userpassword)));
        obj.put("msg","成功");
        return obj.toString();
    }

    //忘记密码
    @RequestMapping(value="changeps",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String changeps(String userphone,String userpassword,String usersecurity,HttpSession session) throws NoSuchAlgorithmException {
        String codes = (String)session.getAttribute("codes");
        System.out.println(userphone+userpassword+usersecurity);
        JSONObject obj = new JSONObject();
        if(usersecurity.equals(codes)){
            Userinfo finduser = userinfoService.finduser(userphone);
            if(finduser!=null){
                userinfoService.changeuser(userphone,  md5Util.md5(new String (userpassword)));
                obj.put("msg","成功");
                return obj.toString();
            }else{
                obj.put("msg","该手机号未注册");
                return obj.toString();
            }
        }else{
            obj.put("msg","验证码输入错误");
            return obj.toString();
        }
    }

    //修改头像
    @RequestMapping(value="updatephoto",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updatephoto(MultipartFile file, HttpSession session) throws IOException {
        Integer userid = (Integer)session.getAttribute("userid");
        String path="F:/项目上传文件存放位置/";
        String userphoto = uploadService.uploadone(file, path);
        //String userphoto= UUID.randomUUID().toString().replace("-", "")+"-"+file;
        userinfoService.updatephoto(userid,userphoto);
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        return obj.toString();
    }

    //物流公司注册
    @RequestMapping(value="enrolllogistics",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrolllogistics(String username,String userphone,String userpassword,String usersecurity,HttpSession session) throws NoSuchAlgorithmException {
        System.out.println(usersecurity);
        JSONObject obj = new JSONObject();
        String codes = (String)session.getAttribute("codes");
        System.out.println(codes);
        if(usersecurity.equals(codes)) {
            Userinfo finduser = userinfoService.finduser(userphone);
            if (finduser == null) {
                Integer userid = null;
                Integer useridentity = 5;
                userinfoService.enrolluser(userid, username, userphone, md5Util.md5(new String(userpassword)), useridentity);
                Userinfo finduser1 = userinfoService.finduser(userphone);
                Integer userid1 = finduser1.getUserid();
                session.setAttribute("userid", userid1);
                obj.put("msg", "成功");
                return obj.toString();
            } else if(finduser!=null){
                obj.put("msg", "该账户已经注册");
                return obj.toString();
            }
        }else {
            obj.put("msg", "验证码错误");
            return obj.toString();
        }
        return null;
    }
}
