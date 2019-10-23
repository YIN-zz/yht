package com.ygt.mapper;

import com.ygt.pojo.Policeinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PoliceinfoDao {

    //警员注册
    @Insert("insert into policeinfo(pphone,pname,paddress,ppassword) values(#{arg0},#{arg1},#{arg2},#{arg3})")
    boolean addpoliceinfo(String pphone, String pname, String paddress, String ppassword);

    //警员登录
    @Select("select * from policeinfo where pphone=#{arg0} and ppassword=#{arg1}")
    Policeinfo findonepolice(String pphone, String ppassword);

    //警员修改密码
    @Update("update policeinfo set ppassword=#{arg1} where pphone=#{arg0)")
    boolean updatepolice(String pphone,String ppassword);

    //警员查看个人信息
    @Select("select * from policeinfo where pphone = #{arg0}")
    Policeinfo findpolices(String pphone);

    //查询警员信息（根据手机号）
    @Select("select pid,pphone,pname,paddress from policeinfo where pphone=#{arg0}")
    Policeinfo findpolice(String pphone);

    //查询所有警员信息
    @Select("select pid,pphone,pname,paddress from policeinfo")
    List<Policeinfo> findallPolice();

}
