package com.ygt.controller;

import com.ygt.pojo.Moments;
import com.ygt.service.MomentsService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class MomentsController {

    @Autowired
    private MomentsService momentsService;

    //上传方法
    @Autowired
    private UploadService uploadService;

    //添加发布朋友圈的信息
    //登录时候保存的session名字、手机号取出来，传至后台（名字、手机号是从前台传的）  可以使用表单的隐藏域上传
    /*@RequestMapping("addmoments")
    public String addmoments(String momentsname,String momentsphone, String momentstext,@RequestParam("myfiles") MultipartFile[] files,
                             HttpServletRequest request){
        Integer momentsid = null;//主键id
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String momentstime = sdf.format(new Date());
        String monepicture  = null;
        String mtwopicture  = null;
        String mthreepicture = null;
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
            momentsService.addmoments(momentsid, momentsname, momentsphone,
                    momentstime, momentstext, monepicture = list.get(0), mtwopicture = list.get(1), mthreepicture = list.get(2));
        }else if(list.size()==2){
            momentsService.addmoments(momentsid, momentsname, momentsphone,
                    momentstime, momentstext, monepicture = list.get(0), mtwopicture = list.get(1), mthreepicture);
        }else if(list.size()==1){
            momentsService.addmoments(momentsid, momentsname, momentsphone,
                    momentstime, momentstext, monepicture = list.get(0), mtwopicture, mthreepicture);
        }else{
            momentsService.addmoments(momentsid, momentsname, momentsphone,
                    momentstime, momentstext, monepicture, mtwopicture, mthreepicture);
        }
        return "index";
    }*/

    //添加发布朋友圈的信息
    //登录时候保存的session名字、手机号取出来，传至后台（名字、手机号是从前台传的）  可以使用表单的隐藏域上传
    @RequestMapping("addmoments")
    public String addNotice(@RequestParam("myfiles") MultipartFile[] multipartFiles, HttpServletRequest request,String momentsname,String momentsphone, String momentstext)throws IOException {
        Integer momentsid = null;//主键id
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String momentstime = sdf.format(new Date());
        String monepicture  = null;
        String mtwopicture  = null;
        String mthreepicture = null;
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
                            monepicture = filename;
                            break;
                        case 1:
                            mtwopicture  = filename;
                            break;
                        case 2:
                            mthreepicture  =filename;
                            break;
                    }
                }
            }
        }
        momentsService.addmoments(momentsid, momentsname, momentsphone,
                momentstime, momentstext, monepicture, mtwopicture, mthreepicture);
        return "index";
    }

    //查看所有发布朋友圈的信息
    @RequestMapping("findallmoments")
    @ResponseBody
    public List<Moments> findallmoments(){
        List<Moments> findallmoments = momentsService.findallmoments();
        return  findallmoments;
    }

    //查看自己发布的朋友圈信息（根据手机号）
    @RequestMapping("findmoments")
    @ResponseBody
    public List<Moments> findmoments(String momentsphone){
        List<Moments> findmoments = momentsService.findmoments(momentsphone);
        return findmoments;
    }

    //详细查看自己发布的某个朋友圈（根据手机号和时间）
    @RequestMapping("findonemoments")
    @ResponseBody
    public Moments findonemoments(String momentstime,String momentsphone){
        Moments findonemoments = momentsService.findonemoments(momentstime, momentsphone);
        return findonemoments;
    }

    //删除发布的朋友圈信息（根据手机号和时间）
    @RequestMapping("deletemoments")
    public String deletemoments(String momentstime,String momentsphone){
        momentsService.deletemoments(momentstime, momentsphone);
        return "index";
    }

    //详细查看朋友圈某条信息（根据名字和时间）
    @RequestMapping("findmoment")
    @ResponseBody
    public Moments findmoment(String momentsname,String momentstime){
        Moments findmoment = momentsService.findmoment(momentsname, momentstime);
        return findmoment;
    }

    //朋友圈信息跟评论信息同时查询出来（以集合的形式）
    @RequestMapping("findallmoment")
    @ResponseBody
    public List<Moments> findallmoment(){
        List<Moments> findallmoment = momentsService.findallmoment();
        return findallmoment;
    }
}
