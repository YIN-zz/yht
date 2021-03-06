package com.ygt.mapper;

import com.ygt.pojo.Driverinfo;
import com.ygt.pojo.Transportinfo;
import com.ygt.pojo.Userinfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface TransportinfoDao {

    //遍历司机运输目的地（司机查看所有运输的信息）（未到达的）
    @Select("select * from driverinfo where driverphone = (select userphone from userinfo where userid = #{userid}) order by driverid desc")
    @Results({@Result(property = "transportinfos",column = "driverid",many = @Many(select = "com.ygt.mapper.TransportinfoDao.findtransportno"))})
    List<Driverinfo> findallbournno(Integer userid);

    //遍历司机运输目的地（司机查看所有运输的信息）（到达）
    @Select("select * from driverinfo where driverphone = (select userphone from userinfo where userid = #{userid}) order by driverid desc")
    @Results({@Result(property = "transportinfos",column = "driverid",many = @Many(select = "com.ygt.mapper.TransportinfoDao.findtransportyes"))})
    List<Driverinfo> findallbournyes(Integer userid);

    //添加货物运输的信息
    @Insert("insert into transportinfo(transporttime,transportstart,transportend,userid) values(#{arg1},#{arg2},#{arg3},#{arg4}) where transportid =#{arg0}")
    boolean enrolltransport(Integer transportid,Date transporttime,String transportstart,String transportend,Integer userid);

    //司机修改运输情况是否出发
    @Update("update transportinfo set transportstart = #{arg1} where transportid = #{arg0}")
    boolean changestart(Integer transportid,String transportstart);

    //司机修改运输情况是否到达
    @Update("update transportinfo set transportend = #{arg1} where transportid = #{arg0}")
    boolean changeend(Integer transportid,String transportend);

    //查询的附表的信息(未到达）
    @Select("select * from transportinfo where driverid = #{arg0} and transportend = '未到达'")
    List<Transportinfo> findtransportno(Integer driverid);
    //查询附表的信息（已经到达）
    @Select("select * from transportinfo where driverid = #{arg0} and transportend = '未到达'")
    List<Transportinfo> findtransportyes(Integer driverid);

}
