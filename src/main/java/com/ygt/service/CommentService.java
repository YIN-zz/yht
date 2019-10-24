package com.ygt.service;

import com.ygt.mapper.CommentDao;
import com.ygt.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    //添加评论信息
    public boolean addcomment(Integer commentid,String commentname,String commenttime,String commenttext,Integer cmomentsid){
        return commentDao.addcomment(commentid, commentname, commenttime, commenttext, cmomentsid);
    }

    //删除评论信息
    public boolean deletecomment(String commentname,String commenttime){
        return commentDao.deletecomment(commentname, commenttime);
    }

    //查看单个评论信息
    public Comment findcomment(String commentname,String commenttime){
        Comment findcomment = commentDao.findcomment(commentname, commenttime);
        return findcomment;
    }

    //查看单个朋友圈的所有评论
    public List<Comment> findallcomment(Integer cmomentsid){
        List<Comment> findallcomment = commentDao.findallcomment(cmomentsid);
        return findallcomment;
    }

    //发布人回复
    public boolean updatecomment(String commentanswer,String commentname,String commenttime){
        return commentDao.updatecomment(commentanswer, commentname,commenttime);
    }
}
