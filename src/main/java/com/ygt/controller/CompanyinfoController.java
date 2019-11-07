package com.ygt.controller;

import com.ygt.pojo.Companyinfo;
import com.ygt.service.CompanyinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/CompanyinfoController")
public class CompanyinfoController {
    @Autowired
    private CompanyinfoService companyinfoService;

    //根据公司名字模糊查询公司信息
    @RequestMapping("allCompanyinfo")
    @ResponseBody
    public List<Companyinfo> allCompanyinfo(String companyname){
        return companyinfoService.allCompanyinfo(companyname);
    }
}
