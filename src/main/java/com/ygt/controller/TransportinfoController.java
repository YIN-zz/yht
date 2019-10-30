package com.ygt.controller;

import com.ygt.pojo.Driverinfo;
import com.ygt.pojo.Transportinfo;
import com.ygt.service.TransportinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("findalldriverinfo")
    @ResponseBody
    public List<Driverinfo> findalldriverinfo(String userphone){
        List<Driverinfo> findalldriverinfo = transportinfoService.findalldriverinfo(userphone);
        return findalldriverinfo;
    }



















    //添加基本运输的信息并将运输备案号transport保存到session中
    /*@RequestMapping("enrolltransport")
    public String enrolltransport(String transport, String tcard, String tbourn, Date ttime,String taddress,HttpSession session) throws NoSuchAlgorithmException {
        session.setAttribute("transport",transport);
        Integer tid = null;
        String tstart = "未出发";
        String tend = "未到达";
        String dphone = (String)session.getAttribute("dphone");
        session.setAttribute("ttime",ttime);
        transportinfoService.addtransportinfo(tid,transport,tcard, tbourn, ttime,tstart,tend,taddress,dphone);
        return "index";
    }*/

    //货物是否开始运输(填写完备案号信息后弹框显示是否显示现在开始运输)(表单修改)
    @RequestMapping("changestarttransport")
    public String changestarttransport(String tstart,HttpSession session){
        /*String transport = (String) session.getAttribute("transport");*/
        String dphone = (String)session.getAttribute("dphone");
        Date ttime = (Date)session.getAttribute("ttime");
        transportinfoService.updatestarttransprot(ttime, tstart,dphone);
        return "index";
    }

    //货物是否开始运输(表单修改)
    /*@RequestMapping("changestart")
    public String changestarttransport(String tstart,HttpSession session){
        String dphone = (String)session.getAttribute("dphone");
        Date ttime = (Date)session.getAttribute("ttime");
        transportinfoService.updatestarttransprot(ttime, tstart,dphone);
        return "redirect:findalltransport";
    }*/

    //修改司机位置信息
    @RequestMapping("updateaddrsss")
    public void updateaddrsss(String taddress,HttpSession session){
        String dphone = (String)session.getAttribute("dphone");
        Date ttime = (Date)session.getAttribute("ttime");
        transportinfoService.updateaddrsss(ttime,taddress,dphone);
    }


    //货物是否运输到达(表单修改)
    @RequestMapping("changeend")
    public String changeendtransport(String tend,HttpSession session){
        String dphone = (String)session.getAttribute("dphone");
        Date ttime = (Date)session.getAttribute("ttime");
        transportinfoService.updateendtransport(ttime, tend,dphone);
        return "redirect:findalltransport";
    }

    //货物是否运输到达（可能用不到）
   /* @RequestMapping("changeendtransport")
    public String changeendtransport(String tend,HttpSession session){
        String dphone = (String)session.getAttribute("dphone");
        Date ttime = (Date)session.getAttribute("ttime");
        transportinfoService.updateendtransport(ttime, tend,dphone);
        return "index";
    }*/

    //查询运输信息（根据运输备案号）
    @RequestMapping("findtransport")
    @ResponseBody
    public Transportinfo findtransport(String transport){
        Transportinfo findtransport = transportinfoService.findtransport(transport);
        return findtransport;
    }

    //查询运输信息（根据手机号，查询司机自己的运输信息）（司机查看个人的dphone是从session中取得）
    //@RequestMapping("findalltransport")
   /* public String findalltransport(Model m, HttpSession session){
        String dphone = (String)session.getAttribute("dphone");
        List<Transportinfo> findalltransport = transportinfoService.findalltransport(dphone);
        m.addAttribute("findalltransport",findalltransport);
        return "index";
    }*/
    //查询运输信息（根据手机号，查询司机自己的运输信息）（司机查看个人的dphone是从session中取得）
    @RequestMapping("findalltransport")
    @ResponseBody
    public List<Transportinfo> findalltransport(HttpSession session){
        String dphone = (String)session.getAttribute("dphone");
        List<Transportinfo> findalltransport = transportinfoService.findalltransport(dphone);
        return findalltransport;
    }

    //查询运输信息（根据输入的手机号查询）
    @RequestMapping("findalltransports")
    @ResponseBody
    public List<Transportinfo> findalltransports(String dphone){
        List<Transportinfo> findalltransport = transportinfoService.findalltransport(dphone);
        return findalltransport;
    }

    //查看所有的异常信息(倒序查看）
    @RequestMapping("findtransports")
    @ResponseBody
    public List<Transportinfo> findtransports(){
        List<Transportinfo> findtransports = transportinfoService.findtransports();
        return findtransports;
    }
}
