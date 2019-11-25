package com.ygt.mapper;

import com.ygt.pojo.Userinfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserinfoDao {

    //用户注册
    @Insert("insert into userinfo(userid,username,userphone,userpassword,useridentity,uservisible) values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5})")
    boolean enrolluser(Integer userid,String username,String userphone,String userpassword,Integer useridentity,Integer uservisible);
    //账号登录
    @Select("select * from userinfo where userphone = #{arg0} and userpassword = #{arg1} and uservisible = 0")
    Userinfo loginuser(String userphone,String userpassword);
    //修改密码
    @Update("update userinfo set userpassword = #{arg1} where userphone = #{arg0}")
    boolean changeuser(String userphone,String userpassword);
    //根据手机号判断是否已经注册
    @Select("select * from userinfo where userphone = #{arg0}")
    Userinfo finduser(String userphone);
    //根据userid查询名字
    @Select("select * from userinfo where userid = #{arg0}")
    Userinfo findusername(Integer userid);
    //修改头像
    @Update("update userinfo set userphoto = #{arg1} where userid = #{arg0}")
    boolean updatephoto(Integer userid,String userphoto);
    //查询企业注册人员
    @Select("select username,userphone from userinfo where uservisible = 0 and userid = (select userid from firminfo where firmname = #{arg0})")
    List<Userinfo> checkus(String firmname);
    //删除企业注册人员(假删除)
    @Delete("update userinfo set uservisible =1 where username = #{arg0} and userphone = #{arg1}")
    boolean remonveus(String username,String userphone);
}
