package com.ygt.mapper;

import com.ygt.pojo.Moments;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MomentsDao {

    //添加发布朋友圈的信息
    @Insert("insert into moments values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5},#{arg6},#{arg7})")
    boolean addmoments(Integer momentsid,String momentsname,String momentsphone,String  momentstime,String momentstext,String monepicture
    ,String mtwopicture,String mthreepicture);

    //查看所有发布朋友圈的信息
    @Select("select * from moments")
    List<Moments> findallmoments();

    //查看自己发布的朋友圈信息（根据手机号）
    @Select("select * from moments where momentsphone = #{arg0}")
    List<Moments> findmoments(String momentsphone);

    //详细查看自己发布的某个朋友圈（根据手机号和时间）
    @Select("select * from moments where momentstime = #{arg0} and momentsphone = #{arg1}")
    Moments findonemoments(String momentstime,String momentsphone);

    //删除发布的朋友圈信息（根据手机号和时间）
    @Delete("delete from moments where momentstime = #{arg0} and momentsphone = #{arg1}")
    boolean deletemoments(String momentstime,String momentsphone);

    //详细查看朋友圈某条信息（根据名字和时间）
    @Select("select * from moments where momentsname = #{arg0} and momentstime = #{arg1}")
    Moments findmoment(String momentsname,String momentstime);

    //朋友圈信息跟评论信息同时查询出来（以集合的形式）
    @Select("select * from moments")
    @Results({@Result(property = "comments",column = "momentsid",many = @Many(select = "com.ygt.mapper.CommentDao.findallcomment"))})
    List<Moments> findallmoment();
}
