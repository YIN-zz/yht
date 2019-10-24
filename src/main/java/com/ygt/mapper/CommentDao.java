package com.ygt.mapper;

import com.ygt.pojo.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommentDao {

    //添加评论信息
    @Insert("insert into comment(commentid,commentname,commenttime,commenttext,cmomentsid) values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4})")
    boolean addcomment(Integer commentid,String commentname,String commenttime,String commenttext,Integer cmomentsid);

    //删除评论信息
    @Delete("delete from comment where commentname = #{arg0} and commenttime = #{arg1}")
    boolean deletecomment(String commentname,String commenttime);

    //查看单个评论信息
    @Select("select * from comment where commentname = #{arg0} and commenttime = #{arg1}")
    Comment findcomment(String commentname, String commenttime);

    //查看单个朋友圈的所有评论
    @Select("select * from comment where cmomentsid = #{arg0}")
    List<Comment> findallcomment(Integer cmomentsid);

    //发布人回复
    @Update("update comment set commentanswer = #{arg0} where commentname = #{arg1} and commenttime = #{arg2}")
    boolean updatecomment(String commentanswer,String commentname,String commenttime);

}
