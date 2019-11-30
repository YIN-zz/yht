package com.ygt.controller;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/easytubepass")
public class UserinfoController {

    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private MD5Util md5Util;
    @Autowired
    private UploadService uploadService;


    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //企管用户注册
    @RequestMapping(value="enrolluser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enrolluser(String username,String userphone,String userpassword,String usersecurity,HttpSession session) throws NoSuchAlgorithmException {
        String codes = (String)session.getAttribute("codes");
        if(usersecurity.equals(codes)) {
            Userinfo finduser = userinfoService.finduser(userphone);
            if (finduser == null) {
                Integer userid = null;
                Integer useridentity = 2;
                Integer uservisible = 0;
                boolean enrolluser = userinfoService.enrolluser(userid, username, userphone, md5Util.md5(new String(userpassword)), useridentity, uservisible);
                if (enrolluser){
                    Userinfo finduser1 = userinfoService.finduser(userphone);
                    Integer userid1 = finduser1.getUserid();
                    session.setAttribute("userid", userid1);
                    result.put("msg", "成功");
                }else{
                    result.put("msg","服务器异常，请稍后重试");
                }
            } else{
                result.put("msg", "该账户已经注册");
            }
        }else {
            result.put("msg", "验证码输入错误");
        }
        return result;
    }
    //账号登录
    @RequestMapping(value="loginuser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> loginuser(String userphone, String userpassword, HttpSession session) throws NoSuchAlgorithmException {
        Userinfo finduser = userinfoService.finduser(userphone);
        if(finduser!=null){
            Userinfo loginuser = userinfoService.loginuser(userphone, md5Util.md5(new String (userpassword)));
            if(loginuser!=null){
                Integer userid = finduser.getUserid();
                session.setAttribute("userid",userid);
                String username = finduser.getUsername();
                String userphoto = finduser.getUserphoto();
                session.setAttribute("userphoto",userphoto);
                Integer useridentity = loginuser.getUseridentity();
                result.put("登录人名字",username);
                result.put("message",useridentity);
                result.put("msg","成功");
            }else{
                result.put("msg","密码输入错误");
            }
        }else {
            result.put("msg","该账户不存在");
        }
        return result;
    }
    //手机验证码登录
    @RequestMapping(value="enteruser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enteruser(String userphone,String usersecurity,HttpSession session){
        String codes = (String)session.getAttribute("codes");
        if(usersecurity.equals(codes)){
            Userinfo finduser = userinfoService.finduser(userphone);
            Integer userid = finduser.getUserid();
            session.setAttribute("userid",userid);
            String username = finduser.getUsername();
            String userphoto = finduser.getUserphoto();
            session.setAttribute("userphoto",userphoto);
            Integer useridentity = finduser.getUseridentity();
            result.put("登录人名字",username);
            result.put("message",useridentity);
            result.put("msg","成功");
        }else{
            result.put("msg","失败");
        }
        return result;
    }

    //修改密码
    @RequestMapping(value="changeuser",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> changeuser(HttpSession session,String userpassword) throws NoSuchAlgorithmException {
        Integer userid = (Integer)session.getAttribute("userid");
        Userinfo findusername = userinfoService.findusername(userid);
        String userphone = findusername.getUserphone();
        boolean changeuser = userinfoService.changeuser(userphone, md5Util.md5(new String(userpassword)));
        if(changeuser){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //忘记密码
    @RequestMapping(value="changeps",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> changeps(String userphone,String userpassword,String usersecurity,HttpSession session) throws NoSuchAlgorithmException {
        String codes = (String)session.getAttribute("codes");
        if(usersecurity.equals(codes)){
            Userinfo finduser = userinfoService.finduser(userphone);
            if(finduser!=null){
                userinfoService.changeuser(userphone,  md5Util.md5(new String (userpassword)));
                result.put("msg","成功");
            }else{
                result.put("msg","该手机号未注册");
            }
        }else{
            result.put("msg","验证码输入错误");
        }
        return result;
    }

    //修改头像
    @RequestMapping(value="updatephoto",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> updatephoto(MultipartFile file, HttpSession session) throws IOException {
        Integer userid = (Integer)session.getAttribute("userid");
        String path="F:/项目上传文件存放位置/";
        String userphoto = uploadService.uploadone(file, path);
        //String userphoto= UUID.randomUUID().toString().replace("-", "")+"-"+file;
        boolean updatephoto = userinfoService.updatephoto(userid, userphoto);
        if(updatephoto){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //物流公司注册
    @RequestMapping(value="enrolllogistics",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enrolllogistics(String username,String userphone,String userpassword,String usersecurity,HttpSession session) throws NoSuchAlgorithmException {
        String codes = (String)session.getAttribute("codes");
        if(usersecurity.equals(codes)) {
            Userinfo finduser = userinfoService.finduser(userphone);
            if (finduser == null) {
                Integer userid = null;
                Integer useridentity = 5;
                Integer uservisible = 0;
                userinfoService.enrolluser(userid, username, userphone, md5Util.md5(new String(userpassword)), useridentity,uservisible);
                Userinfo finduser1 = userinfoService.finduser(userphone);
                Integer userid1 = finduser1.getUserid();
                session.setAttribute("userid", userid1);
                result.put("msg", "成功");
            } else if(finduser!=null){
                result.put("msg", "该账户已经注册");
            }
        }else {
            result.put("msg", "验证码错误");
        }
        return result;
    }

    //查询企业注册人员
    @RequestMapping(value="checkus",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> checkus(String firmname){
        List<Userinfo> checkus = userinfoService.checkus(firmname);
        result.put("message",checkus);
        result.put("msg","成功");
        return result;
    }

    //删除企业注册人员(假删除)
    @RequestMapping(value="remonveus",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> remonveus(String username,String userphone){
        boolean remonveus = userinfoService.remonveus(username, userphone);
        if(remonveus){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }
}
