package com.ygt.controller;

import com.ygt.pojo.*;
import com.ygt.service.AbnormalService;
import com.ygt.service.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/easytubepass")
public class WarningController {

    @Autowired
    private WarningService warningService;
    @Autowired
    private AbnormalService abnormalService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //遍历司机运输目的地（司机查看所有运输的信息）
    @RequestMapping(value="findallwarning",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findallwarning(){
        List<Driverinfo> findallwarning = warningService.findallwarning();
        result.put("message",findallwarning);
        result.put("msg","成功");
        return result;
    }

    //查询某个详细的运输信息
    @RequestMapping(value="findonewarning",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findonewarning(Integer driverid){
        List<Driverinfo> findonewarning = warningService.findonewarning(driverid);
        result.put("message",findonewarning);
        result.put("msg","成功");
        return result;
    }

    //查询某个运输信息的异常上报情况
    @RequestMapping(value="findabnormalwarning",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findabnormalwarning(Integer transportid){
        List<Abnormalinfo> findabnormal = abnormalService.findabnormal(transportid);
        result.put("message",findabnormal);
        result.put("msg","成功");
        return result;
    }

    //数据统计(根据地区查询）
    @RequestMapping(value="findbyaddress",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findbyaddress(String driverbourn){
        List<Driverinfo> findbyaddress = warningService.findbyaddress(driverbourn);
        result.put("message",findbyaddress);
        result.put("msg","成功");
        return result;
    }

    //数据统计(根据企业查询）
    @RequestMapping(value="findbycompany",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findbycompany(String drivercompany){
        List<Driverinfo> findbycompany = warningService.findbycompany(drivercompany);
        result.put("message",findbycompany);
        result.put("msg","成功");
        return result;
    }

    //数据统计(根据时间查询）
    @RequestMapping(value="findbytime",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findbytime(String drivertime){
        List<Driverinfo> findbytime = warningService.findbytime(drivertime);
        result.put("message",findbytime);
        result.put("msg","成功");
        return result;
    }

    //数据统计（根据名称查询）
    @RequestMapping(value="findbyname",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findbyname(String goodname){
        List<StatisticsByName> findbyname = warningService.findbyname(goodname);
        result.put("message",findbyname);
        result.put("msg","成功");
        return result;
    }

    //数据统计（根据异常情况查询）
    @RequestMapping(value="findbyabnormal",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findbyabnormal(String abnormalstatus){
        List<StatisticsByAbnormal> findbyabnormal = warningService.findbyabnormal(abnormalstatus);
        result.put("message",findbyabnormal);
        result.put("msg","成功");
        return result;
    }
}
