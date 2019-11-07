package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.service.LogisticsinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LogisticsController {

    @Autowired
    private LogisticsinfoService logisticsinfoService;

    //物流公司信息的注册
    @RequestMapping(value="enrollLogisticsinfo",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrollLogisticsinfo(String logisticscompany, String logisticsaddress, String logisticsname, String logisticsphone, HttpSession session) {
        Integer logisticsid = null;
        Integer userid = (Integer)session.getAttribute("userid");
        logisticsinfoService.enrollLogisticsinfo(logisticsid,logisticscompany,logisticsaddress,logisticsname,logisticsphone,userid);
        JSONObject obj = new JSONObject();
        obj.put("200","成功");
        return obj.toString();
    }
}
