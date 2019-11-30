package com.ygt.controller;

import com.ygt.pojo.Driverinfo;
import com.ygt.service.TransportinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/easytubepass")
public class TransportinfoController {

    @Autowired
    private TransportinfoService transportinfoService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //遍历司机运输目的地（司机查看所有运输的信息）（未到达的）
    @RequestMapping(value="findallbournno",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findallbournno(HttpSession session){
        Integer userid = (Integer)session.getAttribute("userid");
        List<Driverinfo> findallbournno = transportinfoService.findallbournno(userid);
        result.put("msg","成功");
        result.put("message",findallbournno);
        return result;
    }

    //遍历司机运输目的地（司机查看所有运输的信息）（到达）
    @RequestMapping(value="findallbournyes",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findallbournyes(HttpSession session){
        Integer userid = (Integer)session.getAttribute("userid");
        List<Driverinfo> findallbournyes = transportinfoService.findallbournyes(userid);
        result.put("msg","成功");
        result.put("message",findallbournyes);
        return result;
    }

    //添加货物运输的信息
    @RequestMapping(value="enrolltransport",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enrolltransport(Integer transportid,Date transporttime,String transportstart,String transportend,HttpSession session){
        Integer userid = (Integer)session.getAttribute("userid");
        boolean enrolltransport = transportinfoService.enrolltransport(transportid, transporttime, transportstart, transportend, userid);
        if(enrolltransport){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //司机修改运输情况是否出发
    @RequestMapping(value="changestart",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> changestart(Integer transportid,String transportstart){
        boolean changestart = transportinfoService.changestart(transportid, transportstart);
        if(changestart){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //司机修改运输情况是否到达
    @RequestMapping(value="changeend",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> changeend(Integer transportid,String transportend){
        boolean changeend = transportinfoService.changeend(transportid, transportend);
        if(changeend){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }
}
