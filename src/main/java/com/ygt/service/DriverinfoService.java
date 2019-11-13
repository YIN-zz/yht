package com.ygt.service;

import com.ygt.mapper.DriverinfoDao;
import com.ygt.pojo.Driverinfo;

import com.ygt.pojo.StatisticsByGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DriverinfoService {

    @Autowired
    private DriverinfoDao driverinfoDao;

 //查询所有出入库的信息  根据模糊条件
    public List<StatisticsByGoods> allDriverinfo(@Param("driverrinout")String rinout, @Param("drivertime")String rtime, @Param("goodname")String goodname){

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
    public List<StatisticsByGoods> findalloutgood(String driverbourn,String drivercompany,String drivertime,String goodname){
        List<StatisticsByGoods> findalloutgood = driverinfoDao.findalloutgood(driverbourn, drivercompany, drivertime, goodname);
        return findalloutgood;
    }

    //多个条件模糊查询入库信息
    public List<StatisticsByGoods> findallingood(String driverbourn,String drivercompany,String drivertime,String goodname){
        List<StatisticsByGoods> findallingood = driverinfoDao.findallingood(driverbourn, drivercompany, drivertime, goodname);
        return findallingood;
    }

    //管理查询的 地址 企业 时间 名称 来查询
    public List<StatisticsByGoods> selectAll(@Param("driverbourn") String driverbourn,@Param("drivercompany") String drivercompany, @Param("drivertime") String drivertime, @Param("goodname") String goodname){
        return driverinfoDao.selectAll(driverbourn,drivercompany,drivertime,goodname);
    }

    //出库时查询用户表中有没有司机的信息，没有的话进行注册
    public int selectUserinfo(String username ,String userphone){
        return driverinfoDao.selectUserinfo(username,userphone);
    }
    //注册用户信息表，增加姓名和手机号
    public Boolean insertUserinfo(String username ,String userphone,Integer useridentity){
        return driverinfoDao.insertUserinfo(username,userphone,useridentity);
    }
    //查询到达目的地时所需入库的数据,根据目的地和入库来查询
    public List<StatisticsByGoods> selectDriAll(String driverrinout, String driverbourn){
        return driverinfoDao.selectDriAll(driverrinout,driverbourn);
    }
}
