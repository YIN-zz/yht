package com.ygt.mapper;

import org.apache.ibatis.annotations.Insert;

public interface PoliceinfoDao {

    //添加其余警员的注册
    @Insert("insert into policeinfo values(#{arg0},#{arg1},#{arg2})")
    boolean enrollpolice(Integer policeid,String policeaddress,Integer userid);
}
