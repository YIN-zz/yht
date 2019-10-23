package com.ygt.service;

import com.ygt.mapper.AbnormalDao;
import com.ygt.pojo.Abnormal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbnormalService {

    @Autowired
    private AbnormalDao abnormalDao;

    //添加异常信息
    public boolean addabnormal(Integer aid, String transport,String ainfo, String atime, String aaddress, String acomment, String dphone){
        return abnormalDao.addabnormal(aid,transport,ainfo,atime,aaddress,acomment,dphone);
    }

    //查看异常信息（司机查看自己根据手机号）
    public List<Abnormal> findabnormal(String dphone){
        List<Abnormal> findabnormal = abnormalDao.findabnormal(dphone);
        return findabnormal;
    }

    //查看详细的异常（通过备案证号）
    public Abnormal findoneabnormal(String transport){
        Abnormal findoneabnormal = abnormalDao.findoneabnormal(transport);
        return findoneabnormal;
    }

    //查看异常信息（所有）
    public List<Abnormal> findallabnormal(){
        List<Abnormal> findallabnormal = abnormalDao.findallabnormal();
        return findallabnormal;
    }
}
