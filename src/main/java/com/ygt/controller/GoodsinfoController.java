package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Goodsinfo;
import com.ygt.service.GoodsinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/GoodsinfoController")
public class GoodsinfoController {

    @Autowired
    private GoodsinfoService goodsinfoService;

    //查询出入库货物的信息
    @RequestMapping(value = "findgoodsinfo",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findgoodsinfo(Integer driverid){
        List<Goodsinfo> findgoodsinfo = goodsinfoService.findgoodsinfo(driverid);
        JSONObject obj = new JSONObject();
        obj.put("findgoodsinfo",findgoodsinfo);
        obj.put("200","成功");
        return obj.toString();
    }

    //根据名字模糊查询driverid，返回集合
    @RequestMapping(value = "selectName",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String selectName(String goodname){
        List<Goodsinfo> goodsinfoList = goodsinfoService.selectName(goodname);
        JSONObject obj = new JSONObject();
        obj.put("goodsinfoList",goodsinfoList);
        obj.put("200","成功");
        return obj.toString();
    }
}
