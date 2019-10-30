package com.ygt.service;

import com.ygt.mapper.TransportinfoDao;
import com.ygt.pojo.Driverinfo;
import com.ygt.pojo.Transportinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransportinfoService {

    @Autowired
    private TransportinfoDao transportinfodao;

    public List<Driverinfo> findalldriverinfo(String userphone){
        List<Driverinfo> findalldriverinfo = transportinfodao.findalldriverinfo(userphone);
        return findalldriverinfo;
    }



















    //添加基本运输的信息并将运输备案号transport保存到session中
    /*public boolean addtransportinfo(Integer tid,String transport, String tcard, String tbourn, Date ttime,String tstart,String tend,String taddress,String dphone){
        return transportinfodao.addtransportinfo(tid,transport,tcard, tbourn, ttime,tstart,tend,taddress,dphone);
    }*/

    //货物是否开始运输
    public boolean updatestarttransprot(Date ttime,String tstart,String dphone){
        return transportinfodao.updatestarttransprot(ttime, tstart,dphone);
    }

    //修改司机位置信息
    public boolean updateaddrsss(Date ttime,String taddress,String dphone){
        return transportinfodao.updateaddrsss(ttime, taddress, dphone);
    }

    //货物是否运输到达
    public boolean updateendtransport(Date ttime,String tend,String dphone){
        return transportinfodao.updateendtransport(ttime, tend,dphone);
    }

    //查询运输信息（根据运输备案号）
    public Transportinfo findtransport(String  transport){
        Transportinfo findtransport = transportinfodao.findtransport(transport);
        return findtransport;
    }

    //查询运输信息（根据手机号）
    public List<Transportinfo> findalltransport(String dphone){
        List<Transportinfo> findalltransport = transportinfodao.findalltransport(dphone);
        return findalltransport;
    }

    //查看所有的异常信息(倒序查看）
    public List<Transportinfo> findtransports(){
        List<Transportinfo> findtransports = transportinfodao.findtransports();
        return findtransports;
    }

}
