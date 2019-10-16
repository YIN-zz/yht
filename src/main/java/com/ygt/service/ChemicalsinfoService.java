package com.ygt.service;

import com.ygt.mapper.ChemicalsinfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChemicalsinfoService {
    @Autowired
    private ChemicalsinfoDao chemicalsinfoDao;
    //修改仓库内易制毒设备或化学品的数量和重量
    public Boolean updateChemicalsin(double cwerght,int ccount ,Integer chid){
       return chemicalsinfoDao.updateChemicalsinfo(cwerght, ccount,chid);
    }
}
