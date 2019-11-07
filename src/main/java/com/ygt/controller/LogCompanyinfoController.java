package com.ygt.controller;


import com.ygt.pojo.LogCompanyinfo;
import com.ygt.service.LogCompanyinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/LogCompanyinfoController")
public class LogCompanyinfoController {
    @Autowired
    private LogCompanyinfoService logCompanyinfoService;
    //根据公司名字模糊查询公司信息
    @RequestMapping("allLogCompanyinfo")
    @ResponseBody
    public List<LogCompanyinfo> allLogCompanyinfo(String logcompanyname){
        return logCompanyinfoService.allLogCompanyinfo(logcompanyname);
    }
}
