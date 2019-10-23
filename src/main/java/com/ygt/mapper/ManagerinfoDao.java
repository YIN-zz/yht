package com.ygt.mapper;

import com.ygt.pojo.Managerinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ManagerinfoDao {

    //企管注册
    @Insert("insert into managerinfo(mphone,mname,mpassword,mrelease,maddress,mposition) values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5})")
    boolean addmanager(String mphone,String mname,String mpassword,String mrelease,String maddress,String mposition);

    //企管登录
    @Select("select * from managerinfo where mphone=#{arg0} and mpassword=#{arg1}")
    Managerinfo findmanager(String mphone,String mpassword);

    //企管修改密码
    @Update("update managerinfo set mpassword=#{arg1} where mphone=#{arg0}")
    boolean updatemanager(String mphone,String mpassword);

    //企管查询个人信息
    @Select("select * from mphone where mphone = #{arg0}")
    Managerinfo findmanagerinfo(String mphone);

    //企管查询（按手机号）
    @Select("select mid,mphone,mname,mrelease,maddress,mposition from managerinfo where mphone=#{arg0}")
    Managerinfo findonemanager(String mphone);

    //查询所有注册的企业信息
    @Select("select mid,mphone,mname,mrelease,maddress,mposition from managerinfo")
    List<Managerinfo> findallmanager();

}
