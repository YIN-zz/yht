package com.ygt.service;

import com.ygt.mapper.CommentinfoDao;
import com.ygt.pojo.Commentinfo;
import com.ygt.pojo.Releaseinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentinfoService {

    @Autowired
    private CommentinfoDao commentinfoDao;

    //添加评论信息（连表查询不会返回releaseid）
    public boolean enrollcomment(Integer commentid,String commentname,String commentcontent,String commenttime,Integer releaseid){
        return commentinfoDao.enrollcomment(commentid, commentname, commentcontent, commenttime, releaseid);
    }
    //根据发布名字时间查询releaseid
    public Releaseinfo findreleaseid(String releasename,String releasetime){
        Releaseinfo findreleaseid = commentinfoDao.findreleaseid(releasename, releasetime);
        return findreleaseid;
    }

    //删除评论
    public boolean removecomment(String commentname,String commenttime){
        return commentinfoDao.removecomment(commentname, commenttime);
    }

    //回复评论信息
    public boolean enrollonecomment(String commentname,String commentcontent,String commenttime,Integer releaseid,Integer replyid){
        return commentinfoDao.enrollonecomment(commentname, commentcontent, commenttime, releaseid, replyid);
    }

    //查看单个评论
    public Commentinfo findcomment(Integer commentid){
        Commentinfo findcomment = commentinfoDao.findcomment(commentid);
        return findcomment;
    }
}
