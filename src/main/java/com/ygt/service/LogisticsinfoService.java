package com.ygt.service;

import com.ygt.mapper.LogisticsinfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticsinfoService {

    @Autowired
    private LogisticsinfoDao logisticsinfoDao;

    public boolean enrollLogisticsinfo(Integer logisticsid,String logisticscompany,String logisticsaddress,String logisticsname,String logisticsphone,Integer userid){
        return logisticsinfoDao.enrollLogisticsinfo(logisticsid, logisticscompany, logisticsaddress, logisticsname, logisticsphone, userid);
    }
}
