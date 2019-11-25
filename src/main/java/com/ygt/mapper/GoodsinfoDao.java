package com.ygt.mapper;

import com.ygt.pojo.Goodsinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsinfoDao {

    //添加出入库货物的信息
    @Insert("insert into goodsinfo ( goodtype,goodruse,goodname, goodcount, goodweight,driverid) values(#{goodtype},#{goodruse},#{goodname},#{goodcount},#{goodweight},#{driverid})")
    int addBeiAn(Goodsinfo goodsinfo);

    @Select("select * from goodsinfo where driverid = #{arg}")
    List<Goodsinfo> findgoodsinfo(Integer driverid);

    //根据名字模糊查询driverid，返回集合
    @Select("SELECT * FROM goodsinfo WHERE goodname like CONCAT('%',#{goodname},'%')")
    List<Goodsinfo> selectName(String goodname);
}