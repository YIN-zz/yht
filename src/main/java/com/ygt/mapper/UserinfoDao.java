package com.ygt.mapper;

import com.ygt.pojo.Userinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserinfoDao {

    //用户注册
    @Insert("insert into userinfo values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4})")
    boolean enrolluser(Integer userid,String username,String userphone,String userpassword,Integer useridentity);
    //账号登录
    @Select("select * from userinfo where userphone = #{arg0} and userpassword = #{arg1}")
    Userinfo loginuser(String userphone,String userpassword);
    //修改密码
    @Update("update userinfo set userpassword = #{arg1} where userphone = #{arg0}")
    boolean changeuser(String userphone,String userpassword);
    //根据手机号判断是否已经注册
    @Select("select * from userinfo where userphone = #{arg0}")
    Userinfo finduser(String userphone);
}