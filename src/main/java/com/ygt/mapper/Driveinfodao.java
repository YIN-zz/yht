package com.ygt.mapper;

import com.ygt.pojo.Driveinfo;
import org.apache.ibatis.annotations.Select;

public interface Driveinfodao {

    //根据司机手机号查询司机信息
    @Select("select * from driveinfo where dphone=#{dphone}")
    Driveinfo findDriveinfo(Integer dphoe);
}
