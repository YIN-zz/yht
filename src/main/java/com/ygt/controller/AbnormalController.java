package com.ygt.controller;

import com.ygt.pojo.Abnormal;
import com.ygt.service.AbnormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @RequestMapping("addabnormal")
    public String addabnormal(String transport,String ainfo, String aaddress, String acomment, HttpSession session){
        Integer aid = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String atime = sdf.format(new Date());
        String dphone = (String)session.getAttribute("dphone");
        abnormalService.addabnormal(aid,transport,ainfo,atime,aaddress,acomment,dphone);
        return "index";
    }

    //查看异常信息（司机查看自己根据手机号）
    @RequestMapping("findabnormal")
    @ResponseBody
    public List<Abnormal> findabnormal(HttpSession session){
        String dphone = (String)session.getAttribute("dphone");
        List<Abnormal> findabnormal = abnormalService.findabnormal(dphone);
        return findabnormal;
    }

    //查看详细的异常（通过备案证号）
    @RequestMapping("findoneabnormal")
    @ResponseBody
    public Abnormal findoneabnormal(String transport){
        Abnormal findoneabnormal = abnormalService.findoneabnormal(transport);
        return findoneabnormal;
    }

    //查看异常信息（所有）
    @RequestMapping("findallabnormal")
    @ResponseBody
    public List<Abnormal> findallabnormal(){
        List<Abnormal> findallabnormal = abnormalService.findallabnormal();
        return findallabnormal;
    }
}
