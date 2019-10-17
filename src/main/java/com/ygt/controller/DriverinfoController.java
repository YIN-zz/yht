package com.ygt.controller;

import com.ygt.pojo.Chemicalsinfo;
import com.ygt.pojo.Driverinfo;
import com.ygt.service.ChemicalsinfoService;
import com.ygt.service.DriverinfoService;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DriverinfoController {
    @Autowired
    private DriverinfoService driverinfoService;
    @Autowired
    private ChemicalsinfoService chemicalsinfoService;

    //出入库和时间、货物名称查询
    @RequestMapping("allDriverinfo")
    public String allDriverinfo(String rinout, String rtime, String dname, Model model){
        List<Driverinfo> listDriverinfo = driverinfoService.allDriverinfo(rinout, rtime, dname);
        model.addAttribute("list",listDriverinfo);
        return  "index";
    }

    //出入库信息的添加
    @RequestMapping("addDriverinfo")
    public String addDriverinfo(HttpSession session, Driverinfo driverinfo, String dname){
        Integer sid = Integer.parseInt((String) session.getAttribute("sid"));
        driverinfo.setSid(sid);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String format = dateFormat.format(new Date());
        driverinfo.setRtime(format);
        Integer chid = driverinfoService.selectChemicalsinfo(dname);
        driverinfo.setChid(chid);
        driverinfoService.addDriverinfo(driverinfo);
        Chemicalsinfo chemicalsinfo = chemicalsinfoService.selectChemicalsinfo(chid);
        //对库中化学品和设备总数量和重量进行修改，
        if (driverinfo.getRinout().equals("出库")){
            //出库修改

            double cwerght = chemicalsinfo.getCwerght() - driverinfo.getDwerght();
            int ccount =  chemicalsinfo.getCcount() - driverinfo.getDcount();
            chemicalsinfoService.updateChemicalsin(cwerght,ccount,chid);
        }else {
            //入库修改
            double cwerght = chemicalsinfo.getCwerght() + driverinfo.getDwerght();
            int ccount =  chemicalsinfo.getCcount() + driverinfo.getDcount();
            chemicalsinfoService.updateChemicalsin(cwerght,ccount,chid);
        }


        return "index";
    }
}
