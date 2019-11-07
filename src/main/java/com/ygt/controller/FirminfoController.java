package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Firminfo;
import com.ygt.pojo.Userinfo;
import com.ygt.service.FirminfoService;
import com.ygt.service.UserinfoService;
import com.ygt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
public class FirminfoController {

    @Autowired
    private FirminfoService firminfoService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private MD5Util md5Util;

    //企管信息注册
    @RequestMapping(value="enrollfirm",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrollfirm(String firmname, String firmaddress, String firmprincipal, String firmphone, HttpSession session){
        Integer firmid = null;
        Integer userid = (Integer)session.getAttribute("userid");
        firminfoService.enrollfirm(firmid,firmname,firmaddress,firmprincipal,firmphone,userid);
        JSONObject obj = new JSONObject();
        obj.put("200","成功");
        return obj.toString();
    }

    //添加库管身份信息
    @RequestMapping(value="enrollerStorekeeper",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrollerStorekeeper(String username,String userphone,HttpSession session) throws NoSuchAlgorithmException {
        Integer userid = null;
        Integer useridentity = 3;
        userinfoService.enrolluser(userid,username,userphone, md5Util.md5(new String ("123456")),useridentity);
        Userinfo finduser = userinfoService.finduser(userphone);
        Integer userid1 = finduser.getUserid();
        Integer firmid = null;
        Integer userid2 = (Integer)session.getAttribute("userid");
        Firminfo findfirm = firminfoService.findfirm(userid2);
        String firmname = findfirm.getFirmname();
        String firmaddress = findfirm.getFirmaddress();
        String firmprincipal = findfirm.getFirmprincipal();
        String firmphone = findfirm.getFirmphone();
        firminfoService.enrollfirm(firmid,firmname,firmaddress,firmprincipal,firmphone,userid1);
        JSONObject obj = new JSONObject();
        obj.put("200","成功");
        return obj.toString();
    }
}
