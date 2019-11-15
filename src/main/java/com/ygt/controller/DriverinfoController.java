package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.*;
import com.ygt.service.ChemicalsinfoService;
import com.ygt.service.DriverinfoService;


import com.ygt.service.FirminfoService;
import com.ygt.service.GoodsinfoService;
/*import io.swagger.annotations.Api;*/
/*import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;*/
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/DriverinfoController")
//@Api(value="出入库信息管理",tags = "出入库信息管理接口")
public class DriverinfoController {
    @Autowired
    private DriverinfoService driverinfoService;
    @Autowired
    private ChemicalsinfoService chemicalsinfoService;
     @Autowired
    private GoodsinfoService goodsinfoService;
    @Autowired
    private FirminfoService firminfoService;


    //出入库和时间、货物名称查询
    @RequestMapping(value = "allDriverinfo",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
  //  @ApiOperation("根据出库、入库，时间，货物名称来查询信息")
  //  @ApiImplicitParams(value = {@ApiImplicitParam(name = "driverrinout,drivertime,goodname",value = "出库或者入库,时间,货物名称")})
    public String allDriverinfo(@Param("driverrinout")String driverrinout, @Param("drivertime")String drivertime, @Param("goodname")String goodname){
        List<StatisticsByGoods> list = driverinfoService.allDriverinfo(driverrinout, drivertime, goodname);
        JSONObject obj = new JSONObject();
        obj.put("list",list);
        obj.put("200","成功");
        return  obj.toString();
    }

    //出入库信息的添加
    @RequestMapping(value = "addDriverinfo",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
  // @ApiOperation("添加出库的信息")
  //  @ApiImplicitParams(value = {@ApiImplicitParam(name="Driverinfo",value = "出入库的信息表")})
    public String addDriverinfo(@RequestParam("files") MultipartFile[] multipartFiles, HttpServletRequest request, HttpSession session, Driverinfo driverinfo)throws IOException {
        session.setAttribute("driverrinout",driverinfo.getDriverrinout());
    /*    Integer userid = (Integer) session.getAttribute("userid");
        String company = firminfoService.selectUserFind(userid);
        session.setAttribute("company",company);*/
        session.setAttribute("drivercompany",driverinfo.getDrivercompany());
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
        String format = dateFormat.format(new Date());
        driverinfo.setDrivertime(format);
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
                            driverinfo.setDriverrecordphoto(filename);
                            break;
                        case 1:
                           driverinfo.setDriverphoto(filename);
                            break;
                        case 2:
                            driverinfo.setDrivercarphoto(filename);
                            break;
                    }
                }
            }
        }
        Userinfo userinfo = new Userinfo();
        String username = driverinfo.getDriverdriver();
        String userphone = driverinfo.getDriverphone();
        Integer uid = driverinfoService.selectUserinfo(username, userphone);
        Integer useridentity = 4;
        if (uid == null){
            driverinfoService.insertUserinfo(username,userphone,useridentity);
        }
        Boolean aBoolean = driverinfoService.addDriverinfo(driverinfo);
        session.setAttribute("driverid",driverinfo.getDriverid());
        JSONObject obj = new JSONObject();
        if (aBoolean == true){
            obj.put("200","成功");
            return obj.toString();
        }else {
            obj.put("400","失败");
            return obj.toString();
        }
    }
    //出入库货物的登记
    @RequestMapping(value = "addBeiAnController",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
   // @ApiOperation("添加出库的信息中货物的信息")
  //  @ApiImplicitParams(value = {@ApiImplicitParam(name="Goodsinfo",value = "货物信息表")}
    public String addBeiAnController(Goodsinfo goodsinfo, HttpSession session){
        Integer driverid = (Integer) session.getAttribute("driverid");
        goodsinfo.setDriverid(driverid);
        String company = (String) session.getAttribute("drivercompany");
        Integer chid = driverinfoService.selectChemicalsinfo(goodsinfo.getGoodname());
        goodsinfo.setChid(chid);
        Chemicalsinfo chemicalsinfo = chemicalsinfoService.selectChemicalsinfo(chid,company);
        //对库中化学品和设备总数量和重量进行修改，
        String rinout = (String) session.getAttribute("rinout");
        if (rinout .equals("出库")){
            //出库修改
            double cwerght = chemicalsinfo.getCwerght() - goodsinfo.getGoodeweight();
            int ccount =  chemicalsinfo.getCcount() - goodsinfo.getGoodcount();
            chemicalsinfoService.updateChemicalsin(cwerght,ccount,chid,company);
        }else if(rinout.equals("入库")){
            //入库修改
            double cwerght = chemicalsinfo.getCwerght() + goodsinfo.getGoodeweight();
            int ccount =  chemicalsinfo.getCcount() + goodsinfo.getGoodcount();
            chemicalsinfoService.updateChemicalsin(cwerght,ccount,chid,company);
        }
        int i = goodsinfoService.addBeiAn(goodsinfo);
        JSONObject obj = new JSONObject();
        if (i > 0 ){
            obj.put("200","成功");
            return obj.toString();
        }else {
            obj.put("400","失败");
            return obj.toString();
        }
    }

    //多个条件模糊查询出库信息
    /*@RequestMapping("findalloutgoods")
    @ResponseBody
    private List<Driverinfo> findalloutgoods(String dbourn, String dcompany, String rtime, String goodname) {
        List<Driverinfo> findalloutgoods = driverinfoService.findalloutgoods(dbourn, dcompany, rtime, goodname);
        return findalloutgoods;
    }*/

    //多个条件模糊查询入库信息
   /* @RequestMapping("findallingoods")
    @ResponseBody
    private List<Driverinfo> findallingoods(String dbourn, String dcompany, String rtime, String goodname) {
        List<Driverinfo> findallingoods = driverinfoService.findallingoods(dbourn, dcompany, rtime, goodname);
        return findallingoods;
    }*/

    //多个条件模糊查询出库信息
    @RequestMapping(value = "findalloutgood",produces = "application/json; charset=utf-8")
    @ResponseBody
    private String findalloutgood(String driverbourn,String drivercompany,String drivertime,String goodname){
        List<StatisticsByGoods> findalloutgood = driverinfoService.findalloutgood(driverbourn, drivercompany, drivertime, goodname);
        JSONObject obj = new JSONObject();
        obj.put("findalloutgood",findalloutgood);
        obj.put("200","成功");
        return obj.toString();
    }

    //多个条件模糊查询入库信息
    @RequestMapping(value = "findallingood",produces = "application/json; charset=utf-8")
    @ResponseBody
    private String findallingood(String driverbourn,String drivercompany,String drivertime,String goodname){
        List<StatisticsByGoods> findallingood = driverinfoService.findallingood(driverbourn, drivercompany, drivertime, goodname);
        JSONObject obj = new JSONObject();
        obj.put("findallingood",findallingood);
        obj.put("200","成功");
        return obj.toString();
    }

    //管理查询的 地址 企业 时间 名称 来查询
    @RequestMapping(value = "selectAll",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String selectAll(@Param("driverbourn") String driverbourn,@Param("drivercompany") String drivercompany, @Param("drivertime") String drivertime, @Param("goodname") String goodname){
        List<StatisticsByGoods> list = driverinfoService.selectAll(driverbourn, drivercompany, drivertime, goodname);
        JSONObject obj = new JSONObject();
        obj.put("list",list);
        obj.put("200","成功");
        return obj.toString();
    }

    //查询到达目的地时所需入库的数据,根据目的地和入库来查询
    @RequestMapping(value = "selectDriAll",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String selectDriAll(@Param("driverrinout")String driverrinout, @Param("driverbourn")String driverbourn){
        List<StatisticsByGoods> statisticsByGoods = driverinfoService.selectDriAll(driverrinout, driverbourn);
        JSONObject obj = new JSONObject();
        obj.put("list",statisticsByGoods);
        obj.put("200","成功");
        return obj.toString();
    }

    //企库管运输信息的查询,是否到达，运输情况
    @RequestMapping(value = "selectByAbnormal",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String selectByAbnormal(@Param("driverphone")String driverphone){
        List<StatisticsByAbnormal> statisticsByAbnormals = driverinfoService.selectByAbnormal(driverphone);
        JSONObject obj = new JSONObject();
        obj.put("list",statisticsByAbnormals);
        obj.put("200","成功");
        return obj.toString();

    }
}
