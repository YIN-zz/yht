package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.*;
import com.ygt.service.AbnormalService;
import com.ygt.service.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WarningController {

    @Autowired
    private WarningService warningService;
    @Autowired
    private AbnormalService abnormalService;

    //遍历司机运输目的地（司机查看所有运输的信息）
    @RequestMapping(value="findallwarning",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findallwarning(){
        List<Driverinfo> findallwarning = warningService.findallwarning();
        JSONObject obj = new JSONObject();
        obj.put("message",findallwarning);
        obj.put("msg","成功");
        return obj.toString();
    }

    //查询某个详细的运输信息
    @RequestMapping(value="findonewarning",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findonewarning(Integer driverid){
        List<Driverinfo> findonewarning = warningService.findonewarning(driverid);
        JSONObject obj = new JSONObject();
        obj.put("message",findonewarning);
        obj.put("msg","成功");
        return obj.toString();
    }

    //查询某个运输信息的异常上报情况
    @RequestMapping(value="findabnormalwarning",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findabnormalwarning(Integer transportid){
        List<Abnormalinfo> findabnormal = abnormalService.findabnormal(transportid);
        JSONObject obj = new JSONObject();
        obj.put("message",findabnormal);
        obj.put("msg","成功");
        return obj.toString();
    }

    //数据统计(根据地区查询）
    @RequestMapping(value="findbyaddress",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findbyaddress(String driverbourn){
        List<Driverinfo> findbyaddress = warningService.findbyaddress(driverbourn);
        JSONObject obj = new JSONObject();
        obj.put("message",findbyaddress);
        obj.put("msg","成功");
        return obj.toString();
    }

    //数据统计(根据企业查询）
    @RequestMapping(value="findbycompany",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findbycompany(String drivercompany){
        List<Driverinfo> findbycompany = warningService.findbycompany(drivercompany);
        JSONObject obj = new JSONObject();
        obj.put("message",findbycompany);
        obj.put("msg","成功");
        return obj.toString();
    }

    //数据统计(根据时间查询）
    @RequestMapping(value="findbytime",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findbytime(String drivertime){
        List<Driverinfo> findbytime = warningService.findbytime(drivertime);
        JSONObject obj = new JSONObject();
        obj.put("message",findbytime);
        obj.put("msg","成功");
        return obj.toString();
    }

    //数据统计（根据名称查询）
    @RequestMapping(value="findbyname",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findbyname(String goodname){
        List<StatisticsByName> findbyname = warningService.findbyname(goodname);
        JSONObject obj = new JSONObject();
        obj.put("message",findbyname);
        obj.put("msg","成功");
        return obj.toString();
    }

    //数据统计（根据异常情况查询）
    @RequestMapping(value="findbyabnormal",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findbyabnormal(String abnormalstatus){
        List<StatisticsByAbnormal> findbyabnormal = warningService.findbyabnormal(abnormalstatus);
        JSONObject obj = new JSONObject();
        obj.put("message",findbyabnormal);
        obj.put("msg","成功");
        return obj.toString();
    }
}
