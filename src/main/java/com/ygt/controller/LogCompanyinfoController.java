package com.ygt.controller;


import com.alibaba.fastjson.JSONObject;
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
    @RequestMapping(value = "allLogCompanyinfo",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String allLogCompanyinfo(String logcompanyname){
        List<LogCompanyinfo> logCompanyinfos = logCompanyinfoService.allLogCompanyinfo(logcompanyname);
        JSONObject obj = new JSONObject();
        obj.put("logCompanyinfos",logCompanyinfos);
        obj.put("200","成功");
        return obj.toString();
    }
}
