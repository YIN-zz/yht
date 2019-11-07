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

    //遍历司机运输目的地（司机查看所有运输的信息）
    public List<Driverinfo> findallbourn(Integer userid){
        List<Driverinfo> findallbourn = transportinfodao.findallbourn(userid);
        return findallbourn;
    }
    //查询的附表的信息
    public List<Transportinfo> findtransport(Integer driverid){
        List<Transportinfo> findtransport = transportinfodao.findtransport(driverid);
        return findtransport;
    }

    //添加货物运输的信息
    public boolean enrolltransport(Integer transportid,Date transporttime,String transportstart,String transportend,Integer userid){
        return transportinfodao.enrolltransport(transportid, transporttime, transportstart, transportend, userid);
    }

    //司机修改运输情况是否出发
    public boolean changestart(Integer transportid,String transportstart){
        return transportinfodao.changestart(transportid,transportstart);
    }

    //司机修改运输情况是否到达
    public boolean changeend(Integer transportid,String transportend){
        return transportinfodao.changestart(transportid, transportend);
    }
}
