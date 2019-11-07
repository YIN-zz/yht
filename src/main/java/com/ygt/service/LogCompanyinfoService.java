package com.ygt.service;

import com.ygt.mapper.CompanyinfoDao;
import com.ygt.mapper.LogCompanyinfoDao;
import com.ygt.pojo.Companyinfo;
import com.ygt.pojo.LogCompanyinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogCompanyinfoService {
    @Autowired
    private LogCompanyinfoDao logCompanyinfoDao;
    //根据物流公司名字模糊查询公司信息
  public List<LogCompanyinfo> allLogCompanyinfo(String logcompanyname){
        return logCompanyinfoDao.allLogCompanyinfo(logcompanyname);
    }
}
