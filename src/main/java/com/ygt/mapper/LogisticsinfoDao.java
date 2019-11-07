package com.ygt.mapper;

import org.apache.ibatis.annotations.Insert;

public interface LogisticsinfoDao {

    //添加物流公司注册信息
    @Insert("insert into logisticsinfo values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5})")
    boolean enrollLogisticsinfo(Integer logisticsid,String logisticscompany,String logisticsaddress,String logisticsname,String logisticsphone,Integer userid);
}
