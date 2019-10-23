package com.ygt.mapper;

import com.ygt.pojo.Goodsinfo;
import org.apache.ibatis.annotations.Insert;

public interface GoodsinfoDao {

    //添加出入库货物的信息
    @Insert("insert into beian (recordid, bname, bcwerght, bccount,chid) values(#{recordid},#{bname},#{bcwerght},#{bccount},#{chid})")
    int addBeiAn(Goodsinfo goodsinfo);
}
