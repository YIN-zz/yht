package com.ygt.mapper;

import com.ygt.pojo.Abnormalinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AbnormalDao {

    //添加异常信息
    @Insert("insert into abnormalinfo values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5})")
    boolean enrollabnormal(Integer abnormalid,String abnormalstatus,String abnormalmessage,String abnormaltime,String abnormaladdress,Integer transportid);

    //查看详细的异常（通过transportid)
    @Select("select * from abnormalinfo where transportid = #{arg0}")
    List<Abnormalinfo> findabnormal(Integer transportid);

}
