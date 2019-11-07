package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Commentinfo;
import com.ygt.pojo.Releaseinfo;
import com.ygt.pojo.Userinfo;
import com.ygt.service.CommentinfoService;
import com.ygt.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CommentinfoController {

    @Autowired
    private CommentinfoService commentinfoService;
    @Autowired
    private UserinfoService userinfoService;

    //添加评论信息（连表查询不会返回releaseid）
    @RequestMapping(value = "enrollcomment", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrollcomment(String releasename, String releasetime, String commentcontent, HttpSession session) {
        Integer commentid = null;
        Integer userid = (Integer) session.getAttribute("userid");
        Userinfo findusername = userinfoService.findusername(userid);
        String commentname = findusername.getUsername();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String commenttime = sdf.format(new Date());
        Releaseinfo findreleaseid = commentinfoService.findreleaseid(releasename, releasetime);
        Integer releaseid = findreleaseid.getReleaseid();
        commentinfoService.enrollcomment(commentid, commentname, commentcontent, commenttime, releaseid);
        JSONObject obj = new JSONObject();
        obj.put("200", "成功");
        return obj.toString();
    }

    //删除评论
    @RequestMapping(value = "removecomment", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String removecomment(String commentname, String commenttime) {
        commentinfoService.removecomment(commentname, commenttime);
        JSONObject obj = new JSONObject();
        obj.put("200", "成功");
        return obj.toString();
    }

    //回复评论信息
    @RequestMapping(value = "enrollonecomment", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrollonecomment(String releasename, String releasetime, Integer commentid, String commentcontent, HttpSession session) {
        Integer userid = (Integer) session.getAttribute("userid");
        Userinfo findusername = userinfoService.findusername(userid);
        String commentname = findusername.getUsername();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String commenttime = sdf.format(new Date());
        Releaseinfo findreleaseid = commentinfoService.findreleaseid(releasename, releasetime);
        Integer releaseid = findreleaseid.getReleaseid();
        Integer replyid = commentid;
        commentinfoService.enrollonecomment(commentname, commentcontent, commenttime, releaseid, replyid);
        JSONObject obj = new JSONObject();
        obj.put("200", "成功");
        return obj.toString();
    }

    //查看单个评论
    @RequestMapping(value = "findcomment", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findcomment(Integer commentid){
        Commentinfo findcomment = commentinfoService.findcomment(commentid);
        JSONObject obj = new JSONObject();
        obj.put("200", "成功");
        obj.put("findcomment",findcomment);
        return obj.toString();
    }
}