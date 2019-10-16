package com.ygt.service;

import com.ygt.mapper.Transportinfodao;
import com.ygt.pojo.Transportinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransportinfoService {

    @Autowired
    private Transportinfodao transportinfodao;

    //添加基本运输的信息并将运输备案号transport保存到session中
    public boolean addtransportinfo(Integer tid,String transport, String tcard, String tbourn, Date ttime,String tstart,String tend,String dphone){
        return transportinfodao.addtransportinfo(tid,transport,tcard, tbourn, ttime,tstart,tend,dphone);
    }

    //货物是否开始运输
    public boolean updatestarttransprot(String transport,String tstart){
        return transportinfodao.updatestarttransprot(transport, tstart);
    }

    //货物是否运输到达
    public boolean updateendtransport(String transport,String tend){
        return transportinfodao.updateendtransport(transport, tend);
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
}
