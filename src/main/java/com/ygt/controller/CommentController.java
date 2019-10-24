package com.ygt.controller;

import com.ygt.pojo.Comment;
import com.ygt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    /*@Autowired
    private MomentsService momentsService;*/

    //添加评论信息   id根据名字时间查出来
    @RequestMapping("addcomment")
    public String addcomment(String commentname,String commenttext,Integer cmomentsid){
        Integer commentid = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String commenttime = sdf.format(new Date());
        commentService.addcomment(commentid,commentname,commenttime,commenttext,cmomentsid);
        return "index";
    }

    //删除评论信息
    @RequestMapping("deletecomment")
    public String deletecomment(String commentname,String commenttime){
        commentService.deletecomment(commentname, commenttime);
        return "index";
    }

    //查看单个评论信息
    @RequestMapping("findcomment")
    @ResponseBody
    public Comment findcomment(String commentname,String commenttime){
        Comment findcomment = commentService.findcomment(commentname, commenttime);
        return findcomment;
    }

    //查看单个朋友圈的所有评论
    @RequestMapping("findallcomment")
    @ResponseBody
    public List<Comment> findallcomment(Integer cmomentsid){
        List<Comment> findallcomment = commentService.findallcomment(cmomentsid);
        return findallcomment;
    }

    //发布人回复
    @RequestMapping("updatecomment")
    public String updatecomment(String commentanswer,String commentname,String commenttime){
        commentService.updatecomment(commentanswer, commentname,commenttime);
        return "index";
    }

    /*@RequestMapping("findcomments")
    @ResponseBody
    public List<Comment> findcomments(){
        List<Moments> findallmoments = momentsService.findallmoments();
        System.out.println(findallmoments.size());
        Integer momentsid = null;
        for(int i=0;i<findallmoments.size();i++){
            momentsid = findallmoments.get(i).getMomentsid();
        }
        System.out.println(momentsid);
        List<Comment> findallcomment = commentService.findallcomment(momentsid);
        return findallcomment;
    }*/
}