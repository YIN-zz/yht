package com.ygt.service;

import com.ygt.mapper.NoticeDao;
import com.ygt.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeDao noticeDao;
    //警员添加信息通知
    public int addNotice(Notice notice){
        return noticeDao.addNotice(notice);
    }

    //资讯信息的接收
    public List<Notice> selectNotice(){
        return noticeDao.selectNotice();
    }
}
