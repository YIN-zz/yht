package com.ygt.mapper;

import com.ygt.pojo.Noticeinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoticeinfoDao {

    //警员发布信息的添加
    @Insert("insert into notice(noticemessage, noticetext, noticepicture1, noticepicture2, noticepicture3,noticetime, userid) values(#{noticemessage},#{noticetext},#{noticepicture1},#{noticepicture2},#{noticepicture3}, #{noticetime},#{userid})")
    int addNotice(Noticeinfo notice);


    //资讯信息的查询
    @Select("select * from noticeinfo")
    List<Noticeinfo> selectNotice();
}
