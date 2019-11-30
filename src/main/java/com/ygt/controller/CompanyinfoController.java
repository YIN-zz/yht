package com.ygt.controller;

import com.ygt.pojo.Companyinfo;
import com.ygt.service.CompanyinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/easytubepass")
public class CompanyinfoController {

    @Autowired
    private CompanyinfoService companyinfoService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //根据公司名字模糊查询公司信息
    @RequestMapping(value = "allCompanyinfo",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> allCompanyinfo(String companyname){
        List<Companyinfo> companyinfos = companyinfoService.allCompanyinfo(companyname);
        result.put("companyinfos",companyinfos);
        result.put("msg","成功");
        return result;
    }
}
