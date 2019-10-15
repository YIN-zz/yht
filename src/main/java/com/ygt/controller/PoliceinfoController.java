package com.ygt.controller;

import com.ygt.pojo.Policeinfo;
import com.ygt.service.PoliceinfoService;
import com.ygt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String addpoliceinfo(Integer pphone,String pname,String paddress,String ppassword) throws NoSuchAlgorithmException {
        policeinfoService.addpoliceinfo(pphone,pname,paddress,md5Util.md5(new String (ppassword)));
        return "index";
    }

    //警员登录
    @RequestMapping("loginpolice")
    public String findonepolice(Integer pphone,String ppassword) throws NoSuchAlgorithmException {
        Policeinfo findonepolice = policeinfoService.findonepolice(pphone,md5Util.md5(new String (ppassword)));
        if(findonepolice!=null)
        return "index";
        return "login";
    }

    //警员修改密码
    @RequestMapping("changepolice")
    public String updatepolice(Integer pphone,String ppassword) throws NoSuchAlgorithmException {
        policeinfoService.updatepolice(pphone,md5Util.md5(new String (ppassword)));
        return "login";
    }

    //查询所有警员信息(手机号、名字、地址）
    @RequestMapping("inquirypolice")
    @ResponseBody
    public String findpolice(Model m){
        List<Policeinfo> findallpolice = policeinfoService.findallpolice();
        m.addAttribute("findallpolice",findallpolice);
        return "showpolice";
    }
}