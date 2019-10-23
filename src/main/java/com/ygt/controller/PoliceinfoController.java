package com.ygt.controller;

import com.ygt.pojo.Policeinfo;
import com.ygt.service.PoliceinfoService;
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
public class PoliceinfoController {

    @Autowired
    private PoliceinfoService policeinfoService;

    @Autowired
    private MD5Util md5Util;

    //警员注册
    @RequestMapping("enrollpolice")
    public String addpoliceinfo(String pphone,String pname,String paddress,String ppassword) throws NoSuchAlgorithmException {
        policeinfoService.addpoliceinfo(pphone,pname,paddress,md5Util.md5(new String (ppassword)));
        return "index";
    }

    //警员登录
    @RequestMapping("loginpolice")
    public String findonepolice(String pphone, String ppassword, HttpSession session) throws NoSuchAlgorithmException {
        session.setAttribute("pphone",pphone);
        Policeinfo findonepolice = policeinfoService.findonepolice(pphone,md5Util.md5(new String (ppassword)));
        Integer pid = findonepolice.getPid();
        session.setAttribute("pid",pid);
        String pname = findonepolice.getPname();
        session.setAttribute("pname",pname);
        if(findonepolice!=null)
        return "index";
        return "login";
    }

    //警员修改密码
    @RequestMapping("changepolice")
    public String updatepolice(String pphone,String ppassword) throws NoSuchAlgorithmException {
        policeinfoService.updatepolice(pphone,md5Util.md5(new String (ppassword)));
        return "login";
    }

    //警员查看个人信息
    @RequestMapping("findonepolice")
    @ResponseBody
    public Policeinfo findpolices(HttpSession session){
        String pphone = (String)session.getAttribute("pphone");
        Policeinfo findonepolice = policeinfoService.findpolices(pphone);
        return findonepolice;
    }

    //查询警员信息（根据手机号）
    @RequestMapping("findpolice")
    @ResponseBody
    public Policeinfo findpolice(String pphone){
        Policeinfo findpolice = policeinfoService.findpolice(pphone);
        return findpolice;
    }

    //查询所有警员信息(手机号、名字、地址）
    @RequestMapping("inquirypolice")
    @ResponseBody
    public List<Policeinfo> findpolice(){
        List<Policeinfo> findallpolice = policeinfoService.findallpolice();
        return findallpolice;
    }
}