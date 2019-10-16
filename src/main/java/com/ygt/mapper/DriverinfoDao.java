package com.ygt.mapper;


import com.ygt.pojo.Driverinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.joda.time.DateTime;

import java.awt.*;
import java.util.List;

public interface DriverinfoDao {

    //根据出库 入库和时间，产品名称统计所有信息
    @Select("SELECT * FROM driverinfo WHERE rinout=#{rinout} OR rtime like '%'+#{rtime}+'%' OR dname like '%#{dname}%'")
    List<Driverinfo> allDriverinfo(String rinout, String rtime, String dname);

    //查询商品表的id
    @Select("SELECT chemicalsinfo.chid from chemicalsinfo,driverinfo WHERE chemicalsinfo.chid = driverinfo.chid and chemicalsinfo.cgoods = #{dname}")
      int selectChemicalsinfo(String dname);

    //出入库信息的生成
    @Insert("insert into policeinfo(drid, recordid, dtype, dname, dcount, dwerght, rtime, rdriver, rphone, rnumber, recordphoto, driverphoto, carphoto, ruse, rinout, sid, chid) values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5},#{arg6},#{arg7},#{arg8},#{arg9},#{arg10},#{arg11},#{arg12},#{arg13},#{arg14},#{arg15},#{arg16},#{arg17})")
    boolean addDriverinfo(Driverinfo driverinfo);





}
