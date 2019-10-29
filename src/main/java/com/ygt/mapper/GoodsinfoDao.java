package com.ygt.mapper;

import com.ygt.pojo.Goodsinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsinfoDao {

    //添加出入库货物的信息
    @Insert("insert into goodsinfo ( goodname, goodcount, goodweight,chid,drid) values(#{goodname},#{goodcount},#{goodweight},#{chid},#{drid})")
    int addBeiAn(Goodsinfo goodsinfo);

    @Select("select * from goodsinfo where drid = #{arg}")
    List<Goodsinfo> findgoodsinfo(Integer drid);
}