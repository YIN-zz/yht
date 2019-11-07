package com.ygt.service;

import com.ygt.mapper.CompanyinfoDao;
import com.ygt.pojo.Companyinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyinfoService {
    @Autowired
    private CompanyinfoDao companyinfoDao;
    //根据公司名字模糊查询公司信息
   public List<Companyinfo> allCompanyinfo(String companyname){
        return companyinfoDao.allCompanyinfo(companyname);
    }
}
