package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Userinfo;
import com.ygt.service.SendCodeService;
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
import java.util.UUID;

@Controller
public class UserinfoController {

    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private MD5Util md5Util;
    @Autowired
    private SendCodeService sendCodeService;
    @Autowired
    private UploadService uploadService;

    //企管用户注册
    @RequestMapping(value="enrolluser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrolluser(String username,String userphone,String userpassword,String usersecurity,HttpSession session) throws NoSuchAlgorithmException {
        System.out.println(usersecurity);
        JSONObject obj = new JSONObject();
        String codes = (String)session.getAttribute("codes");
        System.out.println(codes);
        if(usersecurity.equals(codes)) {
            Userinfo finduser = userinfoService.finduser(userphone);
            if (finduser == null) {
                Integer userid = null;
                Integer useridentity = 2;
                userinfoService.enrolluser(userid, username, userphone, md5Util.md5(new String(userpassword)), useridentity);
                Userinfo finduser1 = userinfoService.finduser(userphone);
                Integer userid1 = finduser1.getUserid();
                session.setAttribute("userid", userid1);
                obj.put("200", "成功");
                return obj.toString();
            } else if(finduser!=null){
                obj.put("400", "该账户已经注册");
                return obj.toString();
            }
        }else {
            obj.put("400", "验证码错误");
            return obj.toString();
        }
        return null;
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
            String userphoto = finduser.getUserphoto();
            obj.put("userphoto",userphoto);
            obj.put("200","成功");
            return obj.toString();
        }else{
            obj.put("400","失败");
            return obj.toString();
        }
    }
    //手机验证码登录
    @RequestMapping(value="enteruser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enteruser(String userphone,String usersecurity,HttpSession session){
        JSONObject obj = new JSONObject();
        if(usersecurity.equals(session.getAttribute("codes"))){
            Userinfo finduser = userinfoService.finduser(userphone);
            Integer userid = finduser.getUserid();
            session.setAttribute("userid",userid);
            String userphoto = finduser.getUserphoto();
            obj.put("userphoto",userphoto);
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
        Userinfo finduser = userinfoService.finduser(userphone);
        JSONObject obj = new JSONObject();
        if(finduser!=null){
            userinfoService.changeuser(userphone,  md5Util.md5(new String (userpassword)));
            obj.put("200","成功");
            return obj.toString();
        }else{
            obj.put("400","失败");
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
        obj.put("200","成功");
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
                obj.put("200", "成功");
                return obj.toString();
            } else if(finduser!=null){
                obj.put("400", "该账户已经注册");
                return obj.toString();
            }
        }else {
            obj.put("400", "验证码错误");
            return obj.toString();
        }
        return null;
    }
}
