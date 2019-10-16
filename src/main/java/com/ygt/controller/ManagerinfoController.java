package com.ygt.controller;

import com.ygt.pojo.Managerinfo;
import com.ygt.service.ManagerService;
import com.ygt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class ManagerinfoController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private MD5Util md5Util;

    //企管注册
    @RequestMapping("enrollmanager")
    public String enrollmanager(String mphone,String mname,String mpassword,String mrelease,String maddress,String mposition) throws NoSuchAlgorithmException {
        managerService.addmanagerinfo(mphone, mname, md5Util.md5(new String (mpassword)), mrelease, maddress, mposition);
        return "index";
    }

    //企管登录
    @RequestMapping("loginmanager")
    public String loginmanager(String mphone,String mpassword,HttpSession session) throws NoSuchAlgorithmException {
        session.setAttribute("mphone",mphone);
        Managerinfo findmanager = managerService.findmanager(mphone, md5Util.md5(new String (mpassword)));
        Integer mid = findmanager.getMid();
        session.setAttribute("mid",mid);
        if(findmanager!=null)
            return "index";
        return "login";
    }

    //企管修改密码
    @RequestMapping("changemanager")
    public String changemanager(String mphone,String mpassword) throws NoSuchAlgorithmException {
        managerService.changemanager(mphone, md5Util.md5(new String(mpassword)));
        return "index";
    }

    //企管查询个人信息
    @RequestMapping("findmanager")
    @ResponseBody
    public Managerinfo findmanager(HttpSession session){
        String mphone = (String)session.getAttribute("mphone");
        Managerinfo findonemanager = managerService.findonemanager(mphone);
        return findonemanager;
    }

    //企管查询（按手机号）//跳页面或者返回参数再定
    @RequestMapping("findonemanager")
    @ResponseBody
    public Managerinfo findonemanager(String mphone){
        Managerinfo findonemanager = managerService.findonemanager(mphone);
        return findonemanager;
    }

    //查询所有注册企管信息
    @RequestMapping("findallmanager")
    @ResponseBody
    public List<Managerinfo> findallmanager(){
        List<Managerinfo> managerinfos = managerService.findallmanager();
        return managerinfos;
    }
}
