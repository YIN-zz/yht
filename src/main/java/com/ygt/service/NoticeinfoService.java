package com.ygt.service;

import com.ygt.mapper.NoticeinfoDao;
import com.ygt.pojo.Noticeinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeinfoService {
    @Autowired
    private NoticeinfoDao noticeDao;
    //警员添加信息通知
    public int addNotice(Noticeinfo notice){
        return noticeDao.addNotice(notice);
    }

    //资讯信息的查询
    public List<Noticeinfo> selectNotice(){
        return noticeDao.selectNotice();
    }
}
