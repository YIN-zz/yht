package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Abnormalinfo;
import com.ygt.service.AbnormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AbnormalController {

    @Autowired
    private AbnormalService abnormalService;

    //添加异常信息
    @RequestMapping(value="enrollabnormal",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrollabnormal(String abnormalstatus,String abnormalmessage,String abnormaladdress,Integer transportid){
        Integer abnormalid = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String abnormaltime = sdf.format(new Date());
        abnormalService.enrollabnormal(abnormalid,abnormalstatus,abnormalmessage,abnormaltime,abnormaladdress,transportid);
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        return obj.toString();
    }

    //查看详细的异常（通过transportid)
    @RequestMapping(value="findabnormal",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findabnormal(Integer transportid){
        List<Abnormalinfo> findabnormal = abnormalService.findabnormal(transportid);
        JSONObject obj = new JSONObject();
        obj.put("findabnormal",findabnormal);
        obj.put("msg","成功");
        return obj.toString();
    }
}
