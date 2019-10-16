package com.ygt.mapper;

import com.ygt.pojo.Driveinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface Driveinfodao {

    //司机注册
    @Insert("insert into driveinfo(dphone,dname,dpassword) values(#{arg0},#{arg1},#{arg2})")
    boolean adddriveinfo(String dphone,String dname,String dpassword);

    //司机登录
    @Select("select * from driveinfo where dphone=#{arg0} and dpassword=#{arg1}")
    Driveinfo finddriveinfo(String dphone,String dpassword);

    //司机修改密码
    @Update("update driveinfo set dpassword=#{arg1} where dphone=#{arg0}")
    boolean updatedriveinfo(String dphone,String dpassword);

    //司机查询个人信息
    @Select("select * from driveinfo where dphone = #{arg0}")
    Driveinfo finddrive(String dphone);

    //根据司机手机号查询司机信息
    @Select("select did,dphone,dname from driveinfo where dphone=#{arg0}")
    Driveinfo findonedriveinfo(String dphone);

    //查询所有已经注册的司机
    @Select("select did,dphone,dname from driveinfo")
    List<Driveinfo> findalldriveinfo();

}
