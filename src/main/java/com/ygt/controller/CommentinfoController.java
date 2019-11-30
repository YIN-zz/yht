package com.ygt.controller;

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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/easytubepass")
public class CommentinfoController {

    @Autowired
    private CommentinfoService commentinfoService;
    @Autowired
    private UserinfoService userinfoService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //添加评论信息（连表查询不会返回releaseid）
    @RequestMapping(value = "enrollcomment", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enrollcomment(String releasename, String releasetime, String commentcontent, HttpSession session) {
        Integer commentid = null;
        Integer userid = (Integer) session.getAttribute("userid");
        Userinfo findusername = userinfoService.findusername(userid);
        String commentname = findusername.getUsername();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String commenttime = sdf.format(new Date());
        Releaseinfo findreleaseid = commentinfoService.findreleaseid(releasename, releasetime);
        Integer releaseid = findreleaseid.getReleaseid();
        boolean enrollcomment = commentinfoService.enrollcomment(commentid, commentname, commentcontent, commenttime, releaseid);
        if(enrollcomment){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //删除评论
    @RequestMapping(value = "removecomment", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> removecomment(String commentname, String commenttime) {
        boolean removecomment = commentinfoService.removecomment(commentname, commenttime);
        if(removecomment){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //回复评论信息
    @RequestMapping(value = "enrollonecomment", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enrollonecomment(String releasename, String releasetime, Integer commentid, String commentcontent, HttpSession session) {
        Integer userid = (Integer) session.getAttribute("userid");
        Userinfo findusername = userinfoService.findusername(userid);
        String commentname = findusername.getUsername();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String commenttime = sdf.format(new Date());
        Releaseinfo findreleaseid = commentinfoService.findreleaseid(releasename, releasetime);
        Integer releaseid = findreleaseid.getReleaseid();
        Integer replyid = commentid;
        boolean enrollonecomment = commentinfoService.enrollonecomment(commentname, commentcontent, commenttime, releaseid, replyid);
        if(enrollonecomment){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //查看单个评论
    @RequestMapping(value = "findcomment", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findcomment(Integer commentid){
        Commentinfo findcomment = commentinfoService.findcomment(commentid);
        result.put("msg", "成功");
        result.put("findcomment",findcomment);
        return result;
    }
}