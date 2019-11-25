package com.ygt.mapper;


import com.ygt.pojo.Driverinfo;
import com.ygt.pojo.StatisticsByAbnormal;
import com.ygt.pojo.StatisticsByGoods;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface DriverinfoDao {

    //根据出库 入库和时间，产品名称统计所有信息
    @Select("<script> " +
            "select * from driverinfo " +
            "<where> driverrinout=#{driverrinout} <if test=\"drivertime!=''\"> " +
            "and driverinfo.drivertime like CONCAT('%',#{drivertime},'%') </if> " +
            "<if test=\"goodname!= ''\"> and " +
            "driverid = (select driverid from goodsinfo where goodname = #{goodname}) " +
            "</if> </where> </script>")
    @Results({@Result(property = "goodsinfoList",column = "driverid",many = @Many(select = "com.ygt.mapper.GoodsinfoDao.findgoodsinfo"))})
    // @Select("SELECT * FROM driverinfo,goodsinfo WHERE driverinfo.driverrid=goodsinfo.driverrid AND (driverinfo.driverrinout=#{driverrinout} OR driverinfo.drivertime like CONCAT('%',#{drivertime},'%') OR goodsinfo.goodname like CONCAT('%',#{goodname},'%'))")
    List<Driverinfo> allDriverinfo(@Param("driverrinout")String driverrinout, @Param("drivertime")String drivertime, @Param("goodname")String goodname);

    //查询商品表的id
    @Select("SELECT chemicalsinfo.chid from chemicalsinfo,goodsinfo WHERE chemicalsinfo.chid = goodsinfo.chid and chemicalsinfo.cgoods = #{goodname}")
      int selectChemicalsinfo(String goodname);

    //出入库信息的生成
    @Insert("insert into driverinfo(drivercompany,driverrecordid,  drivertime, driverdriver,drivernumber,driveridentity, driverphone, driverbourn,driverrecordphoto, driverphoto, drivercarphoto, driverrinout,userid) values(#{drivercompany},#{driverrecordid},#{drivertime},#{driverdriver},#{drivernumber},#{driveridentity}, #{driverphone},#{driverbourn},#{driverrecordphoto},#{driverphoto},#{drivercarphoto},#{driverrinout},#{userid})")
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
            "<if test=\"driverbourn!= ''\">" +
            "and driverbourn like CONCAT('%',#{driverbourn},'%')" +
            "</if>" +
            "<if test=\"drivercompany!= ''\">" +
            "and drivercompany like CONCAT('%',#{drivercompany},'%')" +
            "</if> " +
            "<if test=\"drivertime!= ''\">" +
            "and drivertime like CONCAT('%',#{drivertime},'%')" +
            "</if>" +
            "<if test=\"goodname!= ''\">" +
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
            "<if test=\"driverbourn!= ''\">" +
            "and driverbourn like CONCAT('%',#{driverbourn},'%')" +
            "</if>" +
            "<if test=\"drivercompany!= ''\">" +
            "and drivercompany like CONCAT('%',#{drivercompany},'%')" +
            "</if> " +
            "<if test=\"drivertime!= ''\">" +
            "and drivertime like CONCAT('%',#{drivertime},'%')" +
            "</if>" +
            "<if test=\"goodname!= ''\">" +
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
            "<if test=\"driverbourn!= ''\">" +
            "and driverbourn like CONCAT('%',#{driverbourn},'%')" +
            "</if>" +
            "<if test=\"drivercompany!= ''\">" +
            "and drivercompany like CONCAT('%',#{drivercompany},'%')" +
            "</if> " +
            "<if test=\"drivertime!= ''\">" +
            "and drivertime like CONCAT('%',#{drivertime},'%')" +
            "</if>" +
            "<if test=\"goodname!= ''\">" +
            "and driverrid = (select drid from goodsinfo where goodname = #{goodname})" +
            "</if> " +
            "</where> " +
            "</script>")
    @Results({@Result(property = "goodsinfoList",column = "driverid",many = @Many(select = "com.ygt.mapper.GoodsinfoDao.findgoodsinfo"))})
    List<Driverinfo> selectAll(@Param("driverbourn") String driverbourn,@Param("drivercompany") String drivercompany, @Param("drivertime") String drivertime, @Param("goodname") String goodname);



    @Select("SELECT userid FROM userinfo WHERE #{username} and #{userphone}")
    int selectUserinfo(@Param("username")String username ,@Param("userphone")String userphone);
    //出库时查询用户表中有没有司机的信息，没有的话进行注册
    @Insert("INSERT INTO userinfo(username,userphone,useridentity) values(#{username}, #{userphone}, #{useridentity})")
    Boolean insertUserinfo(@Param("username")String username ,@Param("userphone")String userphone,@Param("useridentity")Integer useridentity);

    //查询到达目的地时所需入库的数据,根据目的地和入库来查询
    @Select("SELECT * FROM driverinfo,goodsinfo WHERE driverinfo.driverid=goodsinfo.driverid AND driverrinout=#{driverrinout} AND driverbourn=#{driverbourn}")
    List<StatisticsByGoods> selectDriAll(@Param("driverrinout")String driverrinout, @Param("driverbourn")String driverbourn);

    //企库管运输信息的查询,是否到达，运输情况
    @Select("SELECT * FROM driverinfo,transportinfo WHERE driverinfo.driverid=transportinfo.driverid AND driverinfo.driverphone=#{driverphone}")
    List<StatisticsByAbnormal> selectByAbnormal(@Param("driverphone")String driverphone);

    //查询出入库所有的信息,滚动播放的查询所需
    @Select("SELECT * FROM driverinfo,goodsinfo WHERE driverinfo.driverid=goodsinfo.driverid order by drivertime desc")
    List<StatisticsByGoods> selectDriGood();
}
