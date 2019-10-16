package com.ygt.controller;

import com.ygt.pojo.Transportinfo;
import com.ygt.service.TransportinfoService;
import com.ygt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Controller
public class TransportinfoController {

    @Autowired
    private TransportinfoService transportinfoService;

    @Autowired
    private MD5Util md5Util;

    //添加基本运输的信息并将运输备案号transport保存到session中
    @RequestMapping("enrolltransport")
    public String enrolltransport(String transport, String tcard, String tbourn, Date ttime ,HttpSession session) throws NoSuchAlgorithmException {
        session.setAttribute("transport",transport);
        Integer tid = null;
        String tstart = "未出发";
        String tend = "未到达";
        String dphone = (String)session.getAttribute("dphone");
        transportinfoService.addtransportinfo(tid,transport,md5Util.md5(new String (tcard)), tbourn, ttime,tstart,tend,dphone);
        return "index";
    }

    //货物是否开始运输(填写完备案号信息后弹框显示是否显示现在开始运输)
    @RequestMapping("changestarttransport")
    public String changestarttransport(String tstart,HttpSession session){
        String transport = (String) session.getAttribute("transport");
        transportinfoService.updatestarttransprot(transport,tstart);
        return "index";
    }

    //货物是否开始运输(表单修改)
    @RequestMapping("changestart")
    public String changestarttransport(String transport,String tstart){
        transportinfoService.updatestarttransprot(transport,tstart);
        return "redirect:findalltransport";
    }

    //货物是否运输到达(表单修改)
    @RequestMapping("changeend")
    public String changeendtransport(String transport,String tend){
        transportinfoService.updateendtransport(transport, tend);
        return "redirect:findalltransport";
    }

    //货物是否运输到达（可能用不到）
    @RequestMapping("changeendtransport")
    public String changeendtransport(String tend,HttpSession session){
        String transport = (String)session.getAttribute("transport");
        transportinfoService.updateendtransport(transport, tend);
        return "index";
    }

    //查询运输信息（根据运输备案号）
    @RequestMapping("findtransport")
    @ResponseBody
    public Transportinfo findtransport(String transport){
        Transportinfo findtransport = transportinfoService.findtransport(transport);
        return findtransport;
    }

    //查询运输信息（根据手机号，查询司机自己的运输信息）
    //@RequestMapping("findalltransport")
   /* public String findalltransport(Model m, HttpSession session){
        String dphone = (String)session.getAttribute("dphone");
        List<Transportinfo> findalltransport = transportinfoService.findalltransport(dphone);
        m.addAttribute("findalltransport",findalltransport);
        return "index";
    }*/
    @RequestMapping("findalltransport")
    @ResponseBody
    public List<Transportinfo> findalltransport(HttpSession session){
        String dphone = (String)session.getAttribute("dphone");
        List<Transportinfo> findalltransport = transportinfoService.findalltransport(dphone);
        return findalltransport;
    }
}
