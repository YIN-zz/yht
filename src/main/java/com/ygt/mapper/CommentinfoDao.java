package com.ygt.mapper;

import com.ygt.pojo.Commentinfo;
import com.ygt.pojo.Releaseinfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommentinfoDao {

    //添加评论信息（连表查询不会返回releaseid）
    @Insert("insert into commentinfo(commentid,commentname,commentcontent,commenttime,releaseid) values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5})")
    boolean enrollcomment(Integer commentid,String commentname,String commentcontent,String commenttime,Integer releaseid);
    //根据发布名字时间查询releaseid
    @Select("select * from releaseinfo where releasename = #{arg0} and releasetime = {arg1}")
    Releaseinfo findreleaseid(String releasename,String releasetime);

    //删除评论信息
    @Delete("delete from commentinfo where commentname = #{arg0} and commenttime = #{arg1}")
    boolean removecomment(String commentname,String commenttime);

    //回复评论信息
    @Insert("insert into commentinfo(commentname,commentcontent,commenttime,releaseid,replyid) values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4})")
    boolean enrollonecomment(String commentname,String commentcontent,String commenttime,Integer releaseid,Integer replyid);

    //查看单个评论
    @Select("select * from commentinfo where commentid = #{arg0}")
    Commentinfo findcomment(Integer commentid);
}
