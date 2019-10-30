package com.ygt.mapper;

import com.ygt.pojo.Firminfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface FirminfoDao {

    //企管信息注册
    @Insert("insert into firminfo values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5})")
    boolean enrollfirm(Integer firmid,String firmname,String firmaddress,String firmprincipal,String firmphone,Integer userid);

    //企管根据userid去查看注册的信息
    @Select("select * from firminfo where userid = #{arg0}")
    Firminfo findfirm(Integer userid);

    //根据登录id查询公司的名称
    @Select("SELECT firmname FROM userinfo,firminfo WHERE userinfo.userid=firminfo.userid AND userinfo.userid=#{userid}")
    String selectUserFind(Integer userid);
}
