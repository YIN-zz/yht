package com.ygt.service;

import com.ygt.mapper.PoliceinfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoliceinfoService {

    @Autowired
    private PoliceinfoDao policeinfoDao;

    //添加其余警员的注册
     public boolean enrollpolice(Integer policeid,String policeaddress,Integer userid){
        return policeinfoDao.enrollpolice(policeid, policeaddress, userid);
    }
}
