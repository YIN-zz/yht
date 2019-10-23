package com.ygt.mapper;

import com.ygt.pojo.Abnormal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AbnormalDao {

    //添加异常信息
    @Insert("insert into abnormal(aid,transport,ainfo,atime,aaddress,acomment,dphone) values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5},#{arg6})")
    boolean addabnormal(Integer aid, String transport, String ainfo, String atime, String aaddress, String acomment, String dphone);

    //查看异常信息（司机查看自己根据手机号）
    @Select("select * from abnormal where dphone = #{arg0}")
    List<Abnormal> findabnormal(String dphone);

    //查看详细的异常（通过备案证号）
    @Select("select * from abnormal where transport = #{arg0}")
    Abnormal findoneabnormal(String transport);

    //查看异常信息（所有）
    @Select("select * from abnormal")
    List<Abnormal> findallabnormal();

}
