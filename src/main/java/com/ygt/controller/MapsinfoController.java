package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Mapsinfo;
import com.ygt.service.MapsinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MapsinfoController {

    @Autowired
    private MapsinfoService mapsinfoService;

    JSONObject obj = new JSONObject();

    //根据司机的driverid添加地址信息
    @RequestMapping(value="enrollmap",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrollmap(String mapslongitude,String mapslatitude,Integer driverid){
        Integer mapsid = null;
        mapsinfoService.enrollmap(mapsid, mapslongitude, mapslatitude, driverid);
        obj.put("msg","成功");
        return obj.toString();
    }

    //根据司机的driverid去修改位置信息
    @RequestMapping(value="changemap",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String changemap(String mapslongitude,String mapslatitude,Integer driverid){
        mapsinfoService.changemap(mapslongitude, mapslatitude, driverid);
        obj.put("msg","成功");
        return obj.toString();
    }

    //根据司机的driverid去查看位置信息
    @RequestMapping(value="findmap",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findmap(Integer driverid){
        Mapsinfo findmap = mapsinfoService.findmap(driverid);
        obj.put("msg","成功");
        obj.put("message",findmap);
        return obj.toString();
    }
}
