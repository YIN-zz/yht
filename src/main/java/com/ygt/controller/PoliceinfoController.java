package com.ygt.controller;

import com.ygt.pojo.Policeinfo;
import com.ygt.service.PoliceinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PoliceinfoController {

    @Autowired
    private PoliceinfoService policeinfoService;

    @RequestMapping("addpoliceinfo")
    public String addpoliceinfo(Integer pphone,String pname,String paddress,String ppassword){
        policeinfoService.addpoliceinfo(pphone,pname,paddress,ppassword);
        return "index";
    }

    @RequestMapping("findpolice")
    public String findpolice(){
        List<Policeinfo> findallpolice = policeinfoService.findallpolice();
        return "index";
    }
}