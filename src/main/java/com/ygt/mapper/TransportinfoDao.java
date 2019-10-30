package com.ygt.mapper;

import com.ygt.pojo.Driverinfo;
import com.ygt.pojo.Transportinfo;
import com.ygt.pojo.Userinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface TransportinfoDao {

    //遍历司机运输目的地
    @Select("select * from driverinfo where driverphone = #{arg0}")
    List<Driverinfo> findalldriverinfo(String userphone);

    //添加货物运输的信息
    @Insert("insert into transportinfo values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5})")
    boolean enrolltransport(Integer transportid,String transportbourn,Date transporttime,String transportstart,String transportend,String userphone);
















    //货物是否开始运输
    @Update("update transportinfo set tstart = #{arg1} where ttime = #{arg0} and dphone = #{arg2}")
    boolean updatestarttransprot(Date ttime,String tstart,String dphone);

    //修改司机位置信息
    @Update("update transportinfo set taddress = #{arg1} where ttime =#{arg0} and dphone = #{arg2}")
    boolean updateaddrsss(Date ttime,String taddress,String dphone);

    //货物是否运输到达
    @Update("update transportinfo set tend = #{arg1} where ttime = #{arg0} and dphone = #{arg2}")
    boolean updateendtransport(Date ttime,String tend,String dphone);

    //查询运输信息（根据运输备案号）
    @Select("select * from transportinfo where transport = #{arg0}")
    Transportinfo findtransport(String transport);

    //查询运输信息（根据手机号）
    @Select("select * from transportinfo where dphone = #{arg0}")
    List<Transportinfo> findalltransport(String dphone);

    //查看所有的异常信息(倒序查看）
    @Select("select * from transportinfo order by tid desc")
    List<Transportinfo> findtransports();

}
