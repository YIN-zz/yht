package com.ygt.controller;

import com.ygt.service.LogisticsinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/easytubepass")
public class LogisticsController {

    @Autowired
    private LogisticsinfoService logisticsinfoService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //物流公司信息的注册
    @RequestMapping(value="enrollLogisticsinfo",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enrollLogisticsinfo(String logisticscompany, String logisticsaddress, String logisticsname, String logisticsphone, HttpSession session) {
        Integer logisticsid = null;
        Integer userid = (Integer)session.getAttribute("userid");
        boolean b = logisticsinfoService.enrollLogisticsinfo(logisticsid, logisticscompany, logisticsaddress, logisticsname, logisticsphone, userid);
        if(b){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }
}
