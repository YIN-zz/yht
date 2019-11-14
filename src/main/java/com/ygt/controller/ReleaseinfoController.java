package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Releaseinfo;
import com.ygt.pojo.Userinfo;
import com.ygt.service.ReleaseinfoService;
import com.ygt.service.UserinfoService;
import com.ygt.util.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ReleaseinfoController {

    @Autowired
    private ReleaseinfoService releaseinfoService;
    @Autowired
    private UserinfoService userinfoService;

    //添加发布朋友圈的信息
    @RequestMapping(value="enrollrelease",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrollrelease(@RequestParam("myfiles") MultipartFile[] multipartFiles, String releasecontent, HttpSession session)throws IOException {
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
        releaseinfoService.enrollrelease(releaseid, releasename, releasecontent, releasefirst, releasesecond, releasethird, releasetime);
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        return obj.toString();
    }

    //查看所有的朋友圈发布信息
    @RequestMapping(value="findallrelease",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findallrelease(){
        List<Releaseinfo> findallrelease = releaseinfoService.findallrelease();
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        obj.put("message",findallrelease);
        return obj.toString();
    }

    //查看自己发布的朋友圈信息
    @RequestMapping(value="findrelease",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findrelease(HttpSession session){
        Integer userid = (Integer)session.getAttribute("userid");
        Userinfo findusername = userinfoService.findusername(userid);
        String releasename = findusername.getUsername();
        List<Releaseinfo> findrelease = releaseinfoService.findrelease(releasename);
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        obj.put("message",findrelease);
        return obj.toString();
    }

    //查看单个朋友圈信息
    @RequestMapping(value="findonerelease",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findonerelease(String releasename,String releasetime){
        List<Releaseinfo> findonerelease = releaseinfoService.findonerelease(releasename, releasetime);
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        obj.put("message",findonerelease);
        return obj.toString();
    }

    //删除朋友圈信息
    @RequestMapping(value="removerelease",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String removerelease(String releasetime,HttpSession session){
        Integer userid = (Integer)session.getAttribute("userid");
        Userinfo findusername = userinfoService.findusername(userid);
        String releasename = findusername.getUsername();
        releaseinfoService.removerelease(releasename,releasetime);
        JSONObject obj = new JSONObject();
        obj.put("msg","成功");
        return obj.toString();
    }


    //添加发布朋友圈的信息
    /*@RequestMapping("enrollrelease")
    public String enrollrelease(@RequestParam("myfiles") MultipartFile[] multipartFiles, String releasecontent, HttpSession session){
        Integer releaseid = null;//主键id
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String releasetime = sdf.format(new Date());
        String releasefirst  = null;
        String releasesecond  = null;
        String releasethird = null;
        //文件名字的获取
        List<String> list = new ArrayList<String>();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                list = uploadService.saveFile(request, file, list);
            }
        }
        //写着测试，删了就可以
        for (int i = 0; i < list.size(); i++) {
            System.out.println("成功上传文件：" + list.get(i));
        }
        if(list.size()==3){
            releaseinfoService.enrollrelease(releaseid, releasename, releasecontent, releasefirst = list.get(0), releasesecond = list.get(1), releasethird = list.get(2), releasetime);
        }else if(list.size()==2){
            releaseinfoService.enrollrelease(releaseid, releasename, releasecontent, releasefirst = list.get(0), releasesecond = list.get(1), releasethird, releasetime);
        }else if(list.size()==1){
            releaseinfoService.enrollrelease(releaseid, releasename, releasecontent, releasefirst = list.get(0), releasesecond, releasethird, releasetime);
        }else{
            releaseinfoService.enrollrelease(releaseid, releasename, releasecontent, releasefirst, releasesecond, releasethird, releasetime);
        }
        return "index";
    }*/
}