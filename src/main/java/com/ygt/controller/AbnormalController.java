package com.ygt.controller;

import com.ygt.pojo.Abnormalinfo;
import com.ygt.service.AbnormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/easytubepass")
public class AbnormalController {

    @Autowired
    private AbnormalService abnormalService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //添加异常信息
    @RequestMapping(value="enrollabnormal",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enrollabnormal(String abnormalstatus,String abnormalmessage,String abnormaladdress,Integer transportid){
        Integer abnormalid = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String abnormaltime = sdf.format(new Date());
        boolean enrollabnormal = abnormalService.enrollabnormal(abnormalid, abnormalstatus, abnormalmessage, abnormaltime, abnormaladdress, transportid);
        if(enrollabnormal){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //查看详细的异常（通过transportid)
    @RequestMapping(value="findabnormal",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findabnormal(Integer transportid){
        List<Abnormalinfo> findabnormal = abnormalService.findabnormal(transportid);
        result.put("findabnormal",findabnormal);
        result.put("msg","成功");
        return result;
    }
}
