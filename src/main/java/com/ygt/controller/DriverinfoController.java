package com.ygt.controller;

import com.ygt.pojo.BeiAn;
import com.ygt.pojo.Chemicalsinfo;
import com.ygt.pojo.Driverinfo;
import com.ygt.service.BeiAnService;
import com.ygt.service.ChemicalsinfoService;
import com.ygt.service.DriverinfoService;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DriverinfoController {
    @Autowired
    private DriverinfoService driverinfoService;
    @Autowired
    private ChemicalsinfoService chemicalsinfoService;
    @Autowired
    private BeiAnService beiAnService;



    //出入库和时间、货物名称查询
    @RequestMapping("allDriverinfo")
    public String allDriverinfo(@Param("rinout")String rinout, @Param("rtime")String rtime, @Param("dname")String dname, Model model){
        List<Driverinfo> listDriverinfo = driverinfoService.allDriverinfo(rinout, rtime, dname);
        model.addAttribute("list",listDriverinfo);
        return  "index";
    }

    //出入库信息的添加
    @RequestMapping("addDriverinfo")
    public String addDriverinfo(@RequestParam("files") MultipartFile[] multipartFiles, HttpServletRequest request, HttpSession session, Driverinfo driverinfo)throws IOException {
        session.setAttribute("recordid", driverinfo.getRecordid());
        session.setAttribute("rinout",driverinfo.getRinout());
        session.setAttribute("company",driverinfo.getDcompany());
        Integer mid = Integer.parseInt((String) session.getAttribute("mid"));
        driverinfo.setMid(mid);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String format = dateFormat.format(new Date());
        driverinfo.setRtime(format);
        File file = new File(request.getSession().getServletContext().getRealPath("/upload"));
        if (!file.exists()){
            file.mkdir();
        }
        if(multipartFiles != null && multipartFiles.length > 0) {
            for (int i = 0; i < multipartFiles.length; i++) {
                if (!multipartFiles[i].getOriginalFilename().equals("")) {
                    String filename = multipartFiles[i].getOriginalFilename();
                    File file2 = new File(file, filename);
                    multipartFiles[i].transferTo(file2);
                    switch (i) {
                        case 0:
                            driverinfo.setRecordphoto(filename);
                            break;
                        case 1:
                           driverinfo.setDriverphoto(filename);
                            break;
                        case 2:
                            driverinfo.setCarphoto(filename);
                            break;
                    }
                }
            }
        }
        driverinfoService.addDriverinfo(driverinfo);
        return "index";
    }
    //出入库货物的登记
    public String addBeiAnController(BeiAn beiAn,HttpSession session){
        String recordid = (String) session.getAttribute("recordid");
        beiAn.setRecordid(recordid);
        String company = (String) session.getAttribute("company");
        beiAnService.addBeiAn(beiAn);
        Integer chid = driverinfoService.selectChemicalsinfo(beiAn.getBname());
        beiAn.setChid(chid);
        Chemicalsinfo chemicalsinfo = chemicalsinfoService.selectChemicalsinfo(chid,company);
        //对库中化学品和设备总数量和重量进行修改，
        String rinout = (String) session.getAttribute("rinout");
        if (rinout .equals("出库")){
            //出库修改
            double cwerght = chemicalsinfo.getCwerght() - beiAn.getBcwerght();
            int ccount =  chemicalsinfo.getCcount() - beiAn.getBccount();
            chemicalsinfoService.updateChemicalsin(cwerght,ccount,chid,company);
        }else if(rinout.equals("入库")){
            //入库修改
            double cwerght = chemicalsinfo.getCwerght() + beiAn.getBcwerght();
            int ccount =  chemicalsinfo.getCcount() + beiAn.getBccount();
            chemicalsinfoService.updateChemicalsin(cwerght,ccount,chid,company);
        }
        return "";
    }
}
