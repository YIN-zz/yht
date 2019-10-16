package com.ygt.service;

import com.ygt.mapper.NoticeDao;
import com.ygt.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class NoticeService {
    @Autowired
    private NoticeDao noticeDao;
    //警员添加信息通知
    public int addNotice(Notice notice, HttpSession session){
        return noticeDao.addNotice(notice);
    }
}
