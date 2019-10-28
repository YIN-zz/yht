package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Driveinfo;
import com.ygt.service.DriveinfoService;
import com.ygt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class DriveinfoController {

    @Autowired
    private DriveinfoService driveinfoService;

    @Autowired
    private MD5Util md5Util;

    //司机注册
    @RequestMapping("enrolldrive")
    public String enrolldrive(String dphone,String dname,String dpassword) throws NoSuchAlgorithmException {
        driveinfoService.adddriveinfo(dphone, dname, md5Util.md5(new String (dpassword)));
        return "index";
    }

    //司机登录
    @RequestMapping(value="logindrive",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String logindrive(String dphone, String dpassword, HttpSession session) throws NoSuchAlgorithmException {
        session.setAttribute("dphone",dphone);
        Driveinfo finddriveinfo = driveinfoService.finddriveinfo(dphone, md5Util.md5(new String (dpassword)));
        Integer did = finddriveinfo.getDid();
        session.setAttribute("did",did);
        String dname = finddriveinfo.getDname();
        session.setAttribute("dname",dname);
        JSONObject obj = new JSONObject();
        if(finddriveinfo!=null){
            obj.put("200","成功");
            String s = obj.toString();
            return s;
        }else{
            obj.put("400","失败");
            String s = obj.toString();
            return s;
        }
        /*obj.put("finddriveinfo",finddriveinfo);
        String s = obj.toString();
        return s;*/
        /*if(finddriveinfo!=null)
            return "index";
        return "main";*/
    }

    //司机修改密码
    @RequestMapping("changedrive")
    public String changedrive(String dphone,String dpassword) throws NoSuchAlgorithmException {
        driveinfoService.updatedriveinfo(dphone,md5Util.md5(new String (dpassword)));
        return "index";
    }

    //司机查询个人信息
    @RequestMapping("finddrive")
    @ResponseBody
    public Driveinfo finddrive(HttpSession session){
        String dphone = (String)session.getAttribute("dphone");
        Driveinfo finddrive = driveinfoService.finddrive(dphone);
        return finddrive;
    }

    //根据司机手机号查询司机信息
    @RequestMapping("findonedrive")
    @ResponseBody
    public  Driveinfo findonedrive(String dphone){
        Driveinfo findonedriveinfo = driveinfoService.findonedriveinfo(dphone);
        return findonedriveinfo;
    }

    //查询所有已经注册的司机
    @RequestMapping("findalldriveinfo")
    @ResponseBody
    public List<Driveinfo> findalldriveinfo(){
        List<Driveinfo> findalldriveinfo = driveinfoService.findalldriveinfo();
        return findalldriveinfo;
    }
}
