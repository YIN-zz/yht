package com.ygt.mapper;

import com.ygt.pojo.Transportinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface TransportinfoDao {

    //添加基本运输的信息并将运输备案号transport保存到session中
    @Insert("insert into transportinfo values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5},#{arg6},#{arg7},#{arg8})")
    boolean addtransportinfo(Integer tid,String transport, String tcard, String tbourn, Date ttime,String tstart,String tend,String taddress,String dphone);

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
