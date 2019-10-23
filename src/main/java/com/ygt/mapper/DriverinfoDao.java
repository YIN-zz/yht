package com.ygt.mapper;


import com.ygt.pojo.Driverinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.Date;
import java.util.List;

public interface DriverinfoDao {

    //根据出库 入库和时间，产品名称统计所有信息
 //   @Select("SELECT * FROM driverinfo,beian WHERE driverinfo.recordid=beian.recordid AND (driverinfo.rinout=#{rinout} OR driverinfo.rtime like '%${rtime}%' OR beian.dname like '%${dname}%')")
      @Select("SELECT * FROM driverinfo,beian WHERE driverinfo.recordid=beian.recordid AND (driverinfo.rinout=#{rinout} OR driverinfo.rtime like '%'#{rtime}'%' OR beian.dname like '%'#{dname}'%')")
    List<Driverinfo> allDriverinfo(@Param("rinout")String rinout, @Param("rtime")String rtime, @Param("dname")String dname);

    //查询商品表的id
    @Select("SELECT chemicalsinfo.chid from chemicalsinfo,driverinfo WHERE chemicalsinfo.chid = driverinfo.chid and chemicalsinfo.cgoods = #{dname}")
      int selectChemicalsinfo(String dname);

    //出入库信息的生成
    @Insert("insert into policeinfo(dcompany,recordid, dtype, rtime, rdriver, rphone, dbourn, rnumber, recordphoto, driverphoto, carphoto, ruse, rinout, mid) values(#{dcompany},#{recordid},#{dtype},#{rtime},#{rdriver},#{rphone},#{dbourn},#{rnumber},#{recordphoto},#{driverphoto},#{carphoto},#{ruse},#{rinout},#{mid})")
    boolean addDriverinfo(Driverinfo driverinfo);





    //根据运输到达地区查询       出库（警员查询）
    @Select("select * from driverinfo where recordid = (select transport from transportinfo where tbourn = #{arg0}) and rinout = '出库'")
    List<Driverinfo> findoutaddress(String tbourn);

    //根据运输到达地区查询       入库（警员查询）
    @Select("select * from driverinfo where recordid = (select transport from transportinfo where tbourn = #{arg0}) and rinout = '入库'")
    List<Driverinfo> findinaddress(String tbourn);

    //企业名称（警员查询）    出库
    @Select("select * from driverinfo where dcompany = #{arg0} and and rinout = '出库'")
    Driverinfo findoutrecordid(String dcompany);

    //企业名称（警员查询）    入库
    @Select("select * from driverinfo where dcompany = #{arg0} and and rinout = '入库'")
    Driverinfo findinrecordid(String dcompany);

    //货物名称  出库（警员查询）
    @Select("select * from driverinfo where dname = #{arg0} and and rinout = '出库'")
    List<Driverinfo> findoutdname(String dname);

    //货物名称  入库（警员查询）
    @Select("select * from driverinfo where dname = #{arg0} and and rinout = '入库'")
    List<Driverinfo> findindname(String dname);

    //时间    出库（警员查询）
    @Select("select * from driverinfo where rinout = '出库' and rtime like %#{rtime}%")
    List<Driverinfo> findoutrtime(String rtime);

    //时间    入库（警员查询）
    @Select("select * from driverinfo where rinout = '入库' and rtime like %#{rtime}%")
    List<Driverinfo> findinrtime(String rtime);
}
