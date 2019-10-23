package com.ygt.service;

import com.ygt.mapper.ChemicalsinfoDao;
import com.ygt.pojo.Chemicalsinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChemicalsinfoService {
    @Autowired
    private ChemicalsinfoDao chemicalsinfoDao;

    //根据id、查询易制毒化学品或设备
    public Chemicalsinfo selectChemicalsinfo(@Param("chid")Integer chid,@Param("company")String company){
        return chemicalsinfoDao.selectChemicalsinfo(chid,company);
    }

    //修改仓库内易制毒设备或化学品的数量和重量
    public Boolean updateChemicalsin(@Param("cwerght")double cwerght, @Param("ccount")int ccount, @Param("chid")Integer chid, @Param("company")String company){
       return chemicalsinfoDao.updateChemicalsinfo(cwerght, ccount,chid,company);
    }
}
