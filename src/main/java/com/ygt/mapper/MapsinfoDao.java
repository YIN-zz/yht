package com.ygt.mapper;

import com.ygt.pojo.Mapsinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MapsinfoDao {

    //根据司机的driverid添加地址信息
    @Insert("insert into mapsinfo values(#{arg0},#{arg1},#{arg2},#{arg3})")
    boolean enrollmap(Integer mapsid,String mapslongitude,String mapslatitude,Integer driverid);

    //根据司机的driverid去修改位置信息
    @Update("update mapsinfo set mapslongitude = #{arg0} , mapslatitude = #{arg1} where userid = #{arg2}")
    boolean changemap(String mapslongitude,String mapslatitude,Integer driverid);

    //根据司机的driverid去查看位置信息
    @Select("select * from mapsinfo where driverid = #{arg0}")
    Mapsinfo findmap(Integer driverid);
}
