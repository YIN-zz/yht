package com.ygt.service;

import com.ygt.mapper.DriverinfoDao;
import com.ygt.pojo.Driverinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DriverinfoService {

    @Autowired
    private DriverinfoDao driverinfoDao;

 //查询所有出入库的信息  根据模糊条件
    public List<Driverinfo> allDriverinfo(String rinout, String rtime, String dname){

        return  driverinfoDao.allDriverinfo(rinout,rtime,dname);
    }
    //查询易制毒商品的id
    public int selectChemicalsinfo(String dname){
        return driverinfoDao.selectChemicalsinfo(dname);
    }

    //出入库信息的添加
    public Boolean addDriverinfo(Driverinfo driverinfo){

        return driverinfoDao.addDriverinfo( driverinfo);
    }
}
