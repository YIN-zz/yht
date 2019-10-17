package com.ygt.mapper;

import com.ygt.pojo.Chemicalsinfo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ChemicalsinfoDao {


    //根据id、查询易制毒化学品或设备
    @Select("select cwerght,ccount from chemicalsinfo where chid=#{chid}")
    Chemicalsinfo selectChemicalsinfo(Integer chid);

     //修改仓库内易制毒设备或化学品的数量和重量
    @Update("update chemicalsinfo set cwerght=#{cwerght},ccount=#{ccount) where chid = #{chid}" )
    boolean updateChemicalsinfo(double cwerght,int ccount,Integer chid);
}
