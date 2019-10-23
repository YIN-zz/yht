package com.ygt.service;

import com.ygt.mapper.DriverinfoDao;
import com.ygt.pojo.Driverinfo;

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

    //根据运输到达地区查询    出库（警员查询）
    public List<Driverinfo> findoutaddress(String tbourn){
        List<Driverinfo> findoutaddress = driverinfoDao.findoutaddress(tbourn);
        return findoutaddress;
    }

    //根据运输到达地区查询    入库（警员查询）
    public List<Driverinfo> findinaddress(String tbourn){
        List<Driverinfo> findinaddress = driverinfoDao.findinaddress(tbourn);
        return findinaddress;
    }
}
