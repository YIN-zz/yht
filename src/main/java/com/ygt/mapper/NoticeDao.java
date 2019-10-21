package com.ygt.mapper;

import com.ygt.pojo.Driverinfo;
import com.ygt.pojo.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoticeDao {

    //警员发布信息的添加
    @Insert("insert into notice(nmessage, ntext, nvideo, npicture, ntime, pid) values(#{nmessage},#{ntext},#{nvideo},#{npicture},#{ntime},#{pid})")
    int addNotice(Notice notice);


    //资讯信息的查询
    @Select("select * from notice")
    List<Notice> selectNotice();
}
