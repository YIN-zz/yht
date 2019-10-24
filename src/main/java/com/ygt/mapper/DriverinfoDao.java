package com.ygt.mapper;


import com.ygt.pojo.Driverinfo;
import com.ygt.pojo.Goodsinfo;
import com.ygt.pojo.Moments;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.ResponseBody;


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

    //多个条件模糊查询出库信息
    /*@Select("select * from driverinfo where (dbourn like CONCAT('%',#{arg0},'%') or dcompany like CONCAT('%',#{arg1},'%') or rtime like CONCAT('%',#{arg2},'%') or drid = (select drid from goodsinfo where goodname = #{arg3})) and rinout = '出库'")
    @Results({@Result(property = "goodsinfoList",column = "drid",many = @Many(select = "com.ygt.mapper.GoodsinfoDao.findgoodsinfo"))})
    List<Driverinfo> findalloutgoods(String dbourn,String dcompany,String rtime,String goodname);*/

    //多个条件模糊查询入库信息
   /* @Select("select * from driverinfo where (dbourn like CONCAT('%',#{arg0},'%') or dcompany like CONCAT('%',#{arg1},'%') or rtime like CONCAT('%',#{arg2},'%') or drid = (select drid from goodsinfo where goodname = #{arg3})) and rinout = '入库'")
    @Results({@Result(property = "goodsinfoList",column = "drid",many = @Many(select = "com.ygt.mapper.GoodsinfoDao.findgoodsinfo"))})
    List<Driverinfo> findallingoods(String dbourn,String dcompany,String rtime,String goodname);*/


    //多个条件模糊查询出库信息
    @Select("<script> " +
            "select * from driverinfo " +
            "<where> rinout = '出库' " +
            "<if test=\"dbourn!= null\">" +
            "and dbourn like CONCAT('%',#{dbourn},'%')" +
            "</if>" +
            "<if test=\"dcompany!= null\">" +
            "and dcompany like CONCAT('%',#{dcompany},'%')" +
            "</if> " +
            "<if test=\"rtime!= null\">" +
            "and rtime like CONCAT('%',#{rtime},'%')" +
            "</if>" +
            "<if test=\"goodname!= null\">" +
            "and drid = (select drid from goodsinfo where goodname = #{goodname})" +
            "</if> " +
            "</where> " +
            "</script>")
    @Results({@Result(property = "goodsinfoList",column = "drid",many = @Many(select = "com.ygt.mapper.GoodsinfoDao.findgoodsinfo"))})
    List<Driverinfo> findalloutgood(@Param("dbourn") String dbourn, @Param("dcompany") String dcompany, @Param("rtime") String rtime, @Param("goodname") String goodname);


    //多个条件模糊查询入库信息
    @Select("<script> " +
            "select * from driverinfo " +
            "<where> rinout = '入库' " +
            "<if test=\"dbourn!= null\">" +
            "and dbourn like CONCAT('%',#{dbourn},'%')" +
            "</if>" +
            "<if test=\"dcompany!= null\">" +
            "and dcompany like CONCAT('%',#{dcompany},'%')" +
            "</if> " +
            "<if test=\"rtime!= null\">" +
            "and rtime like CONCAT('%',#{rtime},'%')" +
            "</if>" +
            "<if test=\"goodname!= null\">" +
            "and drid = (select drid from goodsinfo where goodname = #{goodname})" +
            "</if> " +
            "</where> " +
            "</script>")
    @Results({@Result(property = "goodsinfoList",column = "drid",many = @Many(select = "com.ygt.mapper.GoodsinfoDao.findgoodsinfo"))})
    List<Driverinfo> findallingood(@Param("dbourn") String dbourn, @Param("dcompany") String dcompany, @Param("rtime") String rtime, @Param("goodname") String goodname);

}
