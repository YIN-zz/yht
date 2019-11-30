package com.ygt.controller;

import com.ygt.pojo.Mapsinfo;
import com.ygt.service.MapsinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/easytubepass")
public class MapsinfoController {

    @Autowired
    private MapsinfoService mapsinfoService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //根据司机的driverid添加地址信息(测试用的，司机在司机出库哪里添加上)
    @RequestMapping(value="enrollmap",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enrollmap(String mapslongitude,String mapslatitude,Integer driverid){
        Integer mapsid = null;
        boolean enrollmap = mapsinfoService.enrollmap(mapsid, mapslongitude, mapslatitude, driverid);
        if(enrollmap){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //根据司机的driverid去修改位置信息
    @RequestMapping(value="changemap",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> changemap(String mapslongitude,String mapslatitude,Integer driverid){
        boolean changemap = mapsinfoService.changemap(mapslongitude, mapslatitude, driverid);
        if(changemap){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //根据司机的driverid去查看位置信息
    @RequestMapping(value="findmap",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findmap(Integer driverid){
        Mapsinfo findmap = mapsinfoService.findmap(driverid);
        result.put("msg","成功");
        result.put("message",findmap);
        return result;
    }
}
