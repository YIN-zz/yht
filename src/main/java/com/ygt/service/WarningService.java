package com.ygt.service;

import com.ygt.mapper.WarningDao;
import com.ygt.pojo.Driverinfo;
import com.ygt.pojo.StatisticsByAbnormal;
import com.ygt.pojo.StatisticsByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarningService {

    @Autowired
    private WarningDao warningDao;

    //遍历司机运输目的地（司机查看所有运输的信息）
    public List<Driverinfo> findallwarning() {
        List<Driverinfo> findallwarning = warningDao.findallwarning();
        return findallwarning;
    }

    //查询某个详细的运输信息
    public List<Driverinfo> findonewarning(Integer driverid) {
        List<Driverinfo> findonewarning = warningDao.findonewarning(driverid);
        return findonewarning;
    }

    //数据统计(根据地区查询）
    public List<Driverinfo> findbyaddress(String driverbourn) {
        List<Driverinfo> findbyaddress = warningDao.findbyaddress(driverbourn);
        return findbyaddress;
    }

    //数据统计(根据企业查询）
    public List<Driverinfo> findbycompany(String drivercompany) {
        List<Driverinfo> findbycompany = warningDao.findbycompany(drivercompany);
        return findbycompany;
    }

    //数据统计(根据时间查询）
    public List<Driverinfo> findbytime(String drivertime) {
        List<Driverinfo> findbytime = warningDao.findbytime(drivertime);
        return findbytime;
    }

    //数据统计（根据名称查询）
    public List<StatisticsByName> findbyname(String goodname){
        List<StatisticsByName> findbyname = warningDao.findbyname(goodname);
        return findbyname;
    }

    //数据统计（根据异常情况查询）
    public List<StatisticsByAbnormal> findbyabnormal(String abnormalstatus){
        List<StatisticsByAbnormal> findbyabnormal = warningDao.findbyabnormal(abnormalstatus);
        return findbyabnormal;
    }

}
