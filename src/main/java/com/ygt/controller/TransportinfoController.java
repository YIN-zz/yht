package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Driverinfo;
import com.ygt.service.TransportinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class TransportinfoController {

    @Autowired
    private TransportinfoService transportinfoService;

    //遍历司机运输目的地（司机查看所有运输的信息）（未到达的）
    @RequestMapping(value="findallbournno",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findallbournno(HttpSession session){
        Integer userid = (Integer)session.getAttribute("userid");
        List<Driverinfo> findallbournno = transportinfoService.findallbournno(userid);
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        obj.put("message",findallbournno);
        return obj.toString();
    }

    //遍历司机运输目的地（司机查看所有运输的信息）（到达）
    @RequestMapping(value="findallbournyes",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findallbournyes(HttpSession session){
        Integer userid = (Integer)session.getAttribute("userid");
        List<Driverinfo> findallbournyes = transportinfoService.findallbournyes(userid);
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        obj.put("message",findallbournyes);
        return obj.toString();
    }

    //添加货物运输的信息
    @RequestMapping(value="enrolltransport",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrolltransport(Integer transportid,Date transporttime,String transportstart,String transportend,HttpSession session){
        Integer userid = (Integer)session.getAttribute("userid");
        transportinfoService.enrolltransport(transportid,transporttime,transportstart,transportend,userid);
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        return obj.toString();
    }

    //司机修改运输情况是否出发
    @RequestMapping(value="changestart",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String changestart(Integer transportid,String transportstart){
        transportinfoService.changestart(transportid,transportstart);
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        return obj.toString();
    }

    //司机修改运输情况是否到达
    @RequestMapping(value="changeend",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String changeend(Integer transportid,String transportend){
        transportinfoService.changeend(transportid, transportend);
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        return obj.toString();
    }
}
