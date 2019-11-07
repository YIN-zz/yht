package com.ygt.mapper;

import com.ygt.pojo.Companyinfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CompanyinfoDao {
    //根据公司名字模糊查询公司信息
    @Select("SELECT * FROM companyinfo WHERE companyname like CONCAT('%',#{companyname},'%')")
    List<Companyinfo> allCompanyinfo(String companyname);
}
