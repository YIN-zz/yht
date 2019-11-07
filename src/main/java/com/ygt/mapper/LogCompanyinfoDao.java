package com.ygt.mapper;

import com.ygt.pojo.LogCompanyinfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LogCompanyinfoDao {
    //根据物流公司名字模糊查询公司信息
    @Select("SELECT * FROM logcompanyinfo WHERE logcompanyname like CONCAT('%',#{logcompanyname},'%')")
    List<LogCompanyinfo> allLogCompanyinfo(String logcompanyname);
}
