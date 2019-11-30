package com.ygt.controller;

import com.ygt.pojo.Releaseinfo;
import com.ygt.pojo.Userinfo;
import com.ygt.service.ReleaseinfoService;
import com.ygt.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/easytubepass")
public class ReleaseinfoController {

    @Autowired
    private ReleaseinfoService releaseinfoService;
    @Autowired
    private UserinfoService userinfoService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //添加发布朋友圈的信息
    @RequestMapping(value="enrollrelease",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enrollrelease(@RequestParam("myfiles") MultipartFile[] multipartFiles, String releasecontent, HttpSession session)throws IOException {
        Integer releaseid = null;//主键id
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String releasetime = sdf.format(new Date());
        Integer userid = (Integer)session.getAttribute("userid");
        Userinfo findusername = userinfoService.findusername(userid);
        String releasename = findusername.getUsername();
        String releasefirst  = null;
        String releasesecond  = null;
        String releasethird = null;
        File file = new File("F:/项目上传文件存放位置/");
        if (!file.exists()){
            file.mkdir();
        }
        if(multipartFiles != null && multipartFiles.length > 0) {
            for (int i = 0; i < multipartFiles.length; i++) {
                if (!multipartFiles[i].getOriginalFilename().equals("")) {
                    String filename = UUID.randomUUID().toString().replace("-", "")+multipartFiles[i].getOriginalFilename();
                    File file2 = new File(file, filename);
                    multipartFiles[i].transferTo(file2);
                    switch (i) {
                        case 0:
                            releasefirst = filename;
                            break;
                        case 1:
                            releasesecond  = filename;
                            break;
                        case 2:
                            releasethird  =filename;
                            break;
                    }
                }
            }
        }
        boolean enrollrelease = releaseinfoService.enrollrelease(releaseid, releasename, releasecontent, releasefirst, releasesecond, releasethird, releasetime);
        if(enrollrelease){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //查看所有的朋友圈发布信息
    @RequestMapping(value="findallrelease",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findallrelease(){
        List<Releaseinfo> findallrelease = releaseinfoService.findallrelease();
        result.put("msg","成功");
        result.put("message",findallrelease);
        return result;
    }

    //查看自己发布的朋友圈信息
    @RequestMapping(value="findrelease",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findrelease(HttpSession session){
        Integer userid = (Integer)session.getAttribute("userid");
        Userinfo findusername = userinfoService.findusername(userid);
        String releasename = findusername.getUsername();
        List<Releaseinfo> findrelease = releaseinfoService.findrelease(releasename);
        result.put("msg","成功");
        result.put("message",findrelease);
        return result;
    }

    //查看单个朋友圈信息
    @RequestMapping(value="findonerelease",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> findonerelease(String releasename,String releasetime){
        List<Releaseinfo> findonerelease = releaseinfoService.findonerelease(releasename, releasetime);
        result.put("msg","成功");
        result.put("message",findonerelease);
        return result;
    }

    //删除朋友圈信息
    @RequestMapping(value="removerelease",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> removerelease(String releasetime,HttpSession session){
        Integer userid = (Integer)session.getAttribute("userid");
        Userinfo findusername = userinfoService.findusername(userid);
        String releasename = findusername.getUsername();
        boolean removerelease = releaseinfoService.removerelease(releasename, releasetime);
        if(removerelease){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }
}