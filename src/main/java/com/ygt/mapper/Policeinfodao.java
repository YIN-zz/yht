package com.ygt.mapper;

import com.ygt.pojo.Policeinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Policeinfodao {

    @Insert("insert into policeinfo(pphone,pname,paddress,ppassword) values(#{arg0},#{arg1},#{arg2},#{arg3})")
    boolean addpoliceinfo(Integer pphone,String pname,String paddress,String ppassword);

    @Select("select * from policeinfo")
    List<Policeinfo> findallPolice();
    }
