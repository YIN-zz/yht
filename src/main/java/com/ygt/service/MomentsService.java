package com.ygt.service;

import com.ygt.mapper.MomentsDao;
import com.ygt.pojo.Moments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MomentsService {

    @Autowired
    private MomentsDao momentsDao;

    //添加发布朋友圈的信息
    public boolean addmoments(Integer momentsid,String momentsname,String momentsphone,String  momentstime,
                              String momentstext,String monepicture,String mtwopicture,String mthreepicture){
       return momentsDao.addmoments(momentsid, momentsname, momentsphone, momentstime, momentstext, monepicture,
                mtwopicture, mthreepicture);
    }

    //查看所有发布朋友圈的信息
    public List<Moments> findallmoments(){
        List<Moments> findallmoments = momentsDao.findallmoments();
        return findallmoments;
    }

    //查看自己发布的朋友圈信息（根据手机号）
    public List<Moments> findmoments(String momentsphone){
        List<Moments> findmoments = momentsDao.findmoments(momentsphone);
        return findmoments;
    }

    //详细查看自己发布的某个朋友圈（根据手机号和时间）
    public Moments findonemoments(String momentstime,String momentsphone){
        Moments findonemoments = momentsDao.findonemoments(momentstime, momentsphone);
        return findonemoments;
    }

    //删除发布的朋友圈信息（根据手机号和时间）
    public boolean deletemoments(String momentstime,String momentsphone){
        return momentsDao.deletemoments(momentstime, momentsphone);
    }

    //详细查看朋友圈某条信息（根据名字和时间）
    public Moments findmoment(String momentsname,String momentstime){
        Moments findmoment = momentsDao.findmoment(momentsname, momentstime);
        return findmoment;
    }

    //朋友圈信息跟评论信息同时查询出来（以集合的形式）
    public List<Moments> findallmoment(){
        List<Moments> findallmoment = momentsDao.findallmoment();
        return findallmoment;
    }
}
