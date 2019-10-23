package com.ygt.mapper;

import com.ygt.pojo.Chemicalsinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ChemicalsinfoDao {


    //根据id、查询易制毒化学品或设备
    @Select("select cwerght,ccount from chemicalsinfo where chid=#{chid},company=#{company}")
    Chemicalsinfo selectChemicalsinfo(@Param("chid")Integer chid,@Param("company")String company);

     //修改仓库内易制毒设备或化学品的数量和重量
    @Update("update chemicalsinfo set cwerght=#{cwerght},ccount=#{ccount) where chid = #{chid},company=#{company}" )
    boolean updateChemicalsinfo(@Param("cwerght")double cwerght,@Param("ccount")int ccount,@Param("chid")Integer chid,@Param("company")String company);
}
