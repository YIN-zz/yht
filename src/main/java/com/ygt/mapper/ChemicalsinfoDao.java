package com.ygt.mapper;

import org.apache.ibatis.annotations.Update;

public interface ChemicalsinfoDao {

     //修改仓库内易制毒设备或化学品的数量和重量
    @Update("update chemicalsinfo set cwerght=#{arg0},ccount=#{arg1) where chid = #{chid}" )
    boolean updateChemicalsinfo(double cwerght,int ccount,Integer chid);
}
