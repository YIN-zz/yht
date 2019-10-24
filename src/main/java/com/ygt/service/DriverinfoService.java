package com.ygt.service;

import com.ygt.mapper.DriverinfoDao;
import com.ygt.pojo.Driverinfo;

import com.ygt.pojo.Goodsinfo;
import com.ygt.pojo.Moments;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DriverinfoService {

    @Autowired
    private DriverinfoDao driverinfoDao;

 //查询所有出入库的信息  根据模糊条件
    public List allDriverinfo(@Param("rinout")String rinout, @Param("rtime")String rtime, @Param("goodname")String goodname){

        return  driverinfoDao.allDriverinfo(rinout,rtime,goodname);
    }
    //查询易制毒商品的id
    public int selectChemicalsinfo(String dname){
        return driverinfoDao.selectChemicalsinfo(dname);
    }

    //出入库信息的添加
    public Boolean addDriverinfo(Driverinfo driverinfo){

        return driverinfoDao.addDriverinfo( driverinfo);
    }

    //多个条件模糊查询出库信息
    /*public List<Driverinfo> findalloutgoods(String dbourn,String dcompany,String rtime,String goodname){
        List<Driverinfo> findalloutgoods = driverinfoDao.findalloutgoods(dbourn, dcompany, rtime, goodname);
        return findalloutgoods;
    }*/

    //多个条件模糊查询入库信息
    /*public List<Driverinfo> findallingoods(String dbourn,String dcompany,String rtime,String goodname){
        List<Driverinfo> findallingoods = driverinfoDao.findallingoods(dbourn, dcompany, rtime, goodname);
        return findallingoods;
    }*/

    //多个条件模糊查询出库信息
    public List<Driverinfo> findalloutgood(String dbourn,String dcompany,String rtime,String goodname){
        List<Driverinfo> findalloutgood = driverinfoDao.findalloutgood(dbourn, dcompany, rtime, goodname);
        return findalloutgood;
    }

    //多个条件模糊查询入库信息
    public List<Driverinfo> findallingood(String dbourn,String dcompany,String rtime,String goodname){
        List<Driverinfo> findallingood = driverinfoDao.findallingood(dbourn, dcompany, rtime, goodname);
        return findallingood;
    }
}
