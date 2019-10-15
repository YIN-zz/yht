package com.ygt.mapper;

import com.ygt.pojo.Policeinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface Policeinfodao {

    //警员注册
    @Insert("insert into policeinfo(pphone,pname,paddress,ppassword) values(#{arg0},#{arg1},#{arg2},#{arg3})")
    boolean addpoliceinfo(Integer pphone, String pname, String paddress, String ppassword);

    //警员登录
    @Select("select * from policeinfo where pphone=#{arg0} and ppassword=#{arg1}")
    Policeinfo findonepolice(Integer pphone, String ppassword);

    //警员修改密码
    @Update("update policeinfo set ppassword=#{0} where ppassword=#{1)")
    boolean updatepolice(Integer pphone,String ppassword);

    //查询所有警员信息
    @Select("select pphone,pname,paddress from policeinfo")
    List<Policeinfo> findallPolice();

}
