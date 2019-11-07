package com.ygt.service;

import com.ygt.mapper.AbnormalDao;
import com.ygt.pojo.Abnormalinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbnormalService {

    @Autowired
    private AbnormalDao abnormalDao;

    //添加异常信息
    public boolean enrollabnormal(Integer abnormalid,String abnormalstatus,String abnormalmessage,String abnormaltime,String abnormaladdress,Integer transportid){
        return abnormalDao.enrollabnormal(abnormalid,abnormalstatus,abnormalmessage,abnormaltime,abnormaladdress,transportid);
    }

    //查看详细的异常（通过transportid)
    public List<Abnormalinfo> findabnormal(Integer transportid){
        List<Abnormalinfo> findabnormal = abnormalDao.findabnormal(transportid);
        return findabnormal;
    }
}
