package com.ygt.mapper;

import com.ygt.pojo.Transportinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface Transportinfodao {

    //添加基本运输的信息并将运输备案号transport保存到session中
    @Insert("insert into transportinfo values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5},#{arg6},#{arg7})")
    boolean addtransportinfo(Integer tid,String transport, String tcard, String tbourn, Date ttime,String tstart,String tend,String dphone);

    //货物是否开始运输
    @Update("update transportinfo set tstart = #{arg1} where transport = #{arg0}")
    boolean updatestarttransprot(String transport,String tstart);

    //货物是否运输到达
    @Update("update transportinfo set tend = #{arg1} where transport = #{arg0}")
    boolean updateendtransport(String transport,String tend);

    //查询运输信息（根据运输备案号）
    @Select("select * from transportinfo where transport = #{arg0}")
    Transportinfo findtransport(String transport);

    //查询运输信息（根据手机号）
    @Select("select * from transportinfo where dphone = #{arg0}")
    List<Transportinfo> findalltransport(String dphone);
}
