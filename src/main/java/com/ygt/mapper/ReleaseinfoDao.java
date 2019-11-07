package com.ygt.mapper;

import com.ygt.pojo.Commentinfo;
import com.ygt.pojo.Releaseinfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ReleaseinfoDao {

    //添加发布朋友圈的信息
    @Insert("insert into releaseinfo(releaseid,releasename,releasecontent,releasefirst,releasesecond,releasethird,releasetime) values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5},#{arg6})")
    boolean enrollrelease(Integer releaseid,String releasename,String releasecontent,String  releasefirst,String releasesecond,String releasethird,String releasetime);

    //查看所有的朋友圈发布信息
    @Select("select r.releaseid,r.releasename,r.releasecontent,r.releasefirst,r.releasesecond,r.releasethird,r.releasetime,u.userphoto\n" +
            "from releaseinfo r,userinfo u where u.username=r.releasename order by r.releaseid desc")
    @Results({@Result(property = "commentinfos",column = "releaseid",many = @Many(select = "com.ygt.mapper.ReleaseinfoDao.findallcomment"))})
    List<Releaseinfo> findallrelease();

    //查看自己发布的朋友圈信息
    @Select("select r.releaseid,r.releasename,r.releasecontent,r.releasefirst,r.releasesecond,r.releasethird,r.releasetime,u.userphoto\n" +
            "from releaseinfo r,userinfo u where u.username=r.releasename and r.releasename = #{arg0} order by r.releaseid desc")
    @Results({@Result(property = "commentinfos",column = "releaseid",many = @Many(select = "com.ygt.mapper.ReleaseinfoDao.findallcomment"))})
    List<Releaseinfo> findrelease(String releasename);

    //查看单个朋友圈信息
    @Select("select r.releaseid,r.releasename,r.releasecontent,r.releasefirst,r.releasesecond,r.releasethird,r.releasetime,u.userphoto\n" +
               "from releaseinfo r,userinfo u where u.username=r.releasename and r.releasename = #{arg0} and r.releasetime = #{arg1}")
    @Results({@Result(property = "commentinfos",column = "releaseid",many = @Many(select = "com.ygt.mapper.ReleaseinfoDao.findallcomment"))})
    List<Releaseinfo> findonerelease(String releasename,String releasetime);

    //删除朋友圈信息
    @Delete("delete from releaseinfo where releasename = #{arg0} and releasetime = #{arg1}")
    boolean removerelease(String releasename,String releasetime);

    //附表的查询
    @Select("select c.commentid,c.commentname,c.commentcontent,c.commenttime,c.releaseid,c.replyid,u.userphoto\n" +
            "from commentinfo c,userinfo u where u.username=c.commentname and c.releaseid = #{arg0}")
    List<Commentinfo> findallcomment(Integer releaseid);
}
