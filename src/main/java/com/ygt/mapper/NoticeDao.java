package com.ygt.mapper;

import com.ygt.pojo.Driverinfo;
import com.ygt.pojo.Notice;
import org.apache.ibatis.annotations.Insert;

public interface NoticeDao {

    //警员发布信息的添加
    @Insert("insert into notice(nmessage, ntext, nvideo, npicture, pid) values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5})")
    int addNotice(Notice notice);
}
