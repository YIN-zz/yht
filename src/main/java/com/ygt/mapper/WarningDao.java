package com.ygt.mapper;

import com.ygt.pojo.Driverinfo;
import com.ygt.pojo.StatisticsByAbnormal;
import com.ygt.pojo.StatisticsByName;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WarningDao {

    //遍历司机运输目的地（警员查看所有运输的信息）
    @Select("select * from driverinfo order by driverid desc")
    @Results({@Result(property = "transportinfos",column = "driverid",many = @Many(select = "com.ygt.mapper.TransportinfoDao.findtransport"))})
    List<Driverinfo> findallwarning();

    //查询某个详细的运输信息
    @Select("select * from driverinfo where driverid = #{arg0}")
    @Results({@Result(property = "transportinfos",column = "driverid",many = @Many(select = "com.ygt.mapper.TransportinfoDao.findtransport"))})
    List<Driverinfo> findonewarning(Integer driverid);

    //数据统计(根据地区查询）
    @Select("select * from driverinfo where driverbourn like CONCAT('%',#{driverbourn},'%') order by driverid desc")
    @Results({@Result(property = "transportinfos",column = "driverid",many = @Many(select = "com.ygt.mapper.TransportinfoDao.findtransport"))})
    List<Driverinfo> findbyaddress(String driverbourn);

    //数据统计(根据企业查询）
    @Select("select * from driverinfo where drivercompany like CONCAT('%',#{drivercompany},'%') order by driverid desc")
    @Results({@Result(property = "transportinfos",column = "driverid",many = @Many(select = "com.ygt.mapper.TransportinfoDao.findtransport"))})
    List<Driverinfo> findbycompany(String drivercompany);

    //数据统计(根据时间查询）
    @Select("select * from driverinfo where drivertime like CONCAT('%',#{drivertime},'%') order by driverid desc")
    @Results({@Result(property = "transportinfos",column = "driverid",many = @Many(select = "com.ygt.mapper.TransportinfoDao.findtransport"))})
    List<Driverinfo> findbytime(String drivertime);

    //数据统计（根据名称查询）
    @Select("select * from driverinfo,transportinfo,goodsinfo where driverinfo.driverid = goodsinfo.driverid and  transportinfo.driverid = driverinfo.driverid and goodsinfo.goodname like CONCAT('%',#{goodname},'%') order by driverinfo.driverid desc")
    List<StatisticsByName> findbyname(String goodname);

    //数据统计（根据异常情况查询）
    @Select("select * from driverinfo,transportinfo,abnormalinfo where driverinfo.driverid = transportinfo.driverid and  transportinfo.transportid = abnormalinfo.transportid and abnormalinfo.abnormalstatus like CONCAT('%',#{abnormalstatus},'%') order by driverinfo.driverid desc")
    List<StatisticsByAbnormal> findbyabnormal(String abnormalstatus);

}
