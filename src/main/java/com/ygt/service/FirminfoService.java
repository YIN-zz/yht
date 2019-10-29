package com.ygt.service;

import com.ygt.mapper.FirminfoDao;
import com.ygt.pojo.Firminfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirminfoService {

    @Autowired
    private FirminfoDao firminfoDao;

    //企管信息注册
    public boolean enrollfirm(Integer firmid,String firmname,String firmaddress,String firmprincipal,String firmphone,Integer userid){
        return firminfoDao.enrollfirm(firmid, firmname, firmaddress, firmprincipal, firmphone, userid);
    }

    //企管根据userid去查看注册的信息
    public Firminfo findfirm(Integer userid){
        Firminfo findfirm = firminfoDao.findfirm(userid);
        return findfirm;
    }
}
