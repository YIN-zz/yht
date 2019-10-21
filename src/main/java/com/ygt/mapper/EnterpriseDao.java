package com.ygt.mapper;

import com.ygt.pojo.Enterprise;

import org.apache.ibatis.annotations.Insert;

public interface EnterpriseDao {

    //企业主页信息的添加
    @Insert("insert into enterprise(etext, epicture, evideo, einfor, etime, mid) values(#{etext},#{epicture},#{evideo},#{einfor},#{etime},#{mid})")
    int addEnterprise(Enterprise enterprise);
}
