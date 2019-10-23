package com.ygt.mapper;

import com.ygt.pojo.Goodsinfo;
import org.apache.ibatis.annotations.Insert;

public interface GoodsinfoDao {
    //添加出入库商品
    @Insert("insert into goodsinfo(goodname,goodcount,goodweight,drid,chid) values(#{goodname},#{goodcount},#{goodweight},#{drid},#{chid})")
    int addBeiAn(Goodsinfo goodsinfo);



}
