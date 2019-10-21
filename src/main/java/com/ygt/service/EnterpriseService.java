package com.ygt.service;

import com.ygt.mapper.EnterpriseDao;
import com.ygt.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseDao enterpriseDao;
    //增加企业主页信息
    public int addEnterprise(Enterprise enterprise){

        return enterpriseDao.addEnterprise(enterprise);
    }
}
