package com.ygt.mapper;


import com.ygt.pojo.Driverinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.Date;
import java.util.List;

public interface DriverinfoDao {

    //根据出库 入库和时间，产品名称统计所有信息
    @Select("SELECT * FROM driverinfo,beian WHERE driverinfo.recordid=beian.recordid AND driverinfo.rinout=#{rinout} OR driverinfo.rtime like '%${rtime}%' OR beian.dname like '%${dname}%'")
    List<Driverinfo> allDriverinfo(@Param("rinout")String rinout, @Param("rtime")String rtime, @Param("dname")String dname);

    //查询商品表的id
    @Select("SELECT chemicalsinfo.chid from chemicalsinfo,driverinfo WHERE chemicalsinfo.chid = driverinfo.chid and chemicalsinfo.cgoods = #{dname}")
      int selectChemicalsinfo(String dname);

    //出入库信息的生成
    @Insert("insert into policeinfo(dcompany,recordid, dtype, rtime, rdriver, rphone, rnumber, recordphoto, driverphoto, carphoto, ruse, rinout, mid) values(#{dcompany},#{recordid},#{dtype},#{rtime},#{rdriver},#{rphone},#{rnumber},#{recordphoto},#{driverphoto},#{carphoto},#{ruse},#{rinout},#{mid})")
    boolean addDriverinfo(Driverinfo driverinfo);





}
