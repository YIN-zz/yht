package com.ygt.mapper;


import com.ygt.pojo.Abnormal;
import com.ygt.pojo.Driverinfo;
import com.ygt.pojo.Goodsinfo;
import com.ygt.pojo.Moments;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Date;
import java.util.List;

public interface DriverinfoDao {

    //根据出库 入库和时间，产品名称统计所有信息
    @Select("<script> " +
            "select * from driverinfo " +
            "<where> driverrinout=#{driverrinout} <if test=\'drivertime!=null\'> " +
            "and driverinfo.drivertime like CONCAT('%',#{drivertime},'%') </if> " +
            "<if test=\"goodname!= null\"> and " +
            "drid = (select drid from goodsinfo where goodname = #{goodname}) " +
            "</if> </where> </script>")
    @Results({@Result(property = "goodsinfoList",column = "driverid",many = @Many(select = "com.ygt.mapper.GoodsinfoDao.findgoodsinfo"))})
    // @Select("SELECT * FROM driverinfo,goodsinfo WHERE driverinfo.driverrid=goodsinfo.driverrid AND (driverinfo.driverrinout=#{driverrinout} OR driverinfo.drivertime like CONCAT('%',#{drivertime},'%') OR goodsinfo.goodname like CONCAT('%',#{goodname},'%'))")
    List allDriverinfo(@Param("driverrinout")String driverrinout, @Param("drivertime")String drivertime, @Param("goodname")String goodname);

    //查询商品表的id
    @Select("SELECT chemicalsinfo.chid from chemicalsinfo,driverinfo WHERE chemicalsinfo.chid = goodsinfo.chid and chemicalsinfo.cgoods = #{goodname}")
      int selectChemicalsinfo(String dname);

    //出入库信息的生成
    @Insert("insert into driverinfo(drivercompany,driverrecordid,  drivertime, driverdriver,drivernumber,driveridentity, driverphone, driverbourn,driverrecordphoto, driverphoto, drivercarphoto, driverrinout) values(#{drivercompany},#{driverrecordid},#{drivertime},#{driverdriver},#{drivernumber},#{driveridentity}, #{driverphone},#{driverbourn},#{driverrecordphoto},#{driverphoto},#{drivercarphoto},#{driverrinout})")
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
            "<where> driverrinout = '出库' " +
            "<if test=\"driverbourn!= null\">" +
            "and driverbourn like CONCAT('%',#{driverbourn},'%')" +
            "</if>" +
            "<if test=\"drivercompany!= null\">" +
            "and drivercompany like CONCAT('%',#{drivercompany},'%')" +
            "</if> " +
            "<if test=\"drivertime!= null\">" +
            "and drivertime like CONCAT('%',#{drivertime},'%')" +
            "</if>" +
            "<if test=\"goodname!= null\">" +
            "and driverrid = (select driverrid from goodsinfo where goodname = #{goodname})" +
            "</if> " +
            "</where> " +
            "</script>")
    @Results({@Result(property = "goodsinfoList",column = "driverid",many = @Many(select = "com.ygt.mapper.GoodsinfoDao.findgoodsinfo"))})
    List<Driverinfo> findalloutgood(@Param("driverbourn") String driverbourn, @Param("drivercompany") String drivercompany, @Param("drivertime") String drivertime, @Param("goodname") String goodname);


    //多个条件模糊查询入库信息
    @Select("<script> " +
            "select * from driverinfo " +
            "<where> driverrinout = '入库' " +
            "<if test=\"driverbourn!= null\">" +
            "and driverbourn like CONCAT('%',#{driverbourn},'%')" +
            "</if>" +
            "<if test=\"drivercompany!= null\">" +
            "and drivercompany like CONCAT('%',#{drivercompany},'%')" +
            "</if> " +
            "<if test=\"drivertime!= null\">" +
            "and drivertime like CONCAT('%',#{drivertime},'%')" +
            "</if>" +
            "<if test=\"goodname!= null\">" +
            "and driverrid = (select driverrid from goodsinfo where goodname = #{goodname})" +
            "</if> " +
            "</where> " +
            "</script>")
    @Results({@Result(property = "goodsinfoList",column = "driverid",many = @Many(select = "com.ygt.mapper.GoodsinfoDao.findgoodsinfo"))})
    List<Driverinfo> findallingood(@Param("driverbourn") String driverbourn, @Param("drivercompany") String drivercompany, @Param("drivertime") String drivertime, @Param("goodname") String goodname);

    //管理查询的地址 企业 时间 名称 查询
    @Select("<script> " +
            "select * from driverinfo " +
            "<where> " +
            "<if test=\"driverbourn!= null\">" +
            "and driverbourn like CONCAT('%',#{driverbourn},'%')" +
            "</if>" +
            "<if test=\"drivercompany!= null\">" +
            "and drivercompany like CONCAT('%',#{drivercompany},'%')" +
            "</if> " +
            "<if test=\"drivertime!= null\">" +
            "and drivertime like CONCAT('%',#{drivertime},'%')" +
            "</if>" +
            "<if test=\"goodname!= null\">" +
            "and driverrid = (select drid from goodsinfo where goodname = #{goodname})" +
            "</if> " +
            "</where> " +
            "</script>")
    @Results({@Result(property = "goodsinfoList",column = "driverid",many = @Many(select = "com.ygt.mapper.GoodsinfoDao.findgoodsinfo"))})
    List selectAll(@Param("driverbourn") String driverbourn,@Param("drivercompany") String drivercompany, @Param("drivertime") String drivertime, @Param("goodname") String goodname);



    @Select("SELECT userid FROM userinfo WHERE #{username} and #{userphone}")
    int selectUserinfo(String username ,String userphone);
    //出库时查询用户表中有没有司机的信息，没有的话进行注册
    @Insert("INSERT INTO userinfo(username,userphone,useridentity) values(#{username}, #{userphone}, #{useridentity})")
    Boolean insertUserinfo(String username ,String userphone,Integer useridentity);

}
