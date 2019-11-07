package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class UploadVideo {

    //上传视频
    @RequestMapping(value = "/addImg.do",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addImg(HttpServletRequest request, @RequestParam(value = "vtime") String vtime,
                         String text, @RequestParam(value = "file", required = false) MultipartFile pictureFile,
                         @RequestParam(value = "videofile", required = false) MultipartFile pictureFile1) throws Exception {
        //这个RequestParam(value="file")的是我们在前台的name=“file”
        // 使用UUID给图片重命名，并去掉四个“-”
        if (text != "") {//判断视频标题是否重名
            String name = UUID.randomUUID().toString().replaceAll("-", "");
            // 获取文件的扩展名
            String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
            // 设置图片上传路径
            // 以绝对路径保存重名命后的图片
            pictureFile.transferTo(new File("D:/video/" + name + "." + ext));
            // 把图片存储路径保存到数据库
            String vname = UUID.randomUUID().toString().replaceAll("-", "");
            // 获取视频的扩展名
            String vext = FilenameUtils.getExtension(pictureFile1.getOriginalFilename());
            // 设置视频上传路径
            // 以绝对路径保存重名命后的图片
            pictureFile1.transferTo(new File("D:/video/" + vname + "." + vext));
            // 把视频存储路径保存到数据库
            System.out.println("有没有收到text" + text);
            //http://localhost:8080/video/这个是本地视频前缀，为了实现本地视频的网页播放，
           //搭建一个tomcat图片服务器就行了
           /* String murl = "http://localhost:8080/video/" + name + "." + ext;
            String vurl = "http://localhost:8080/video/" + vname + "." + vext;*/
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String time = df.format(new Date());
            System.out.println("步骤1");
            String videosrc = "D:/video/" + vname + "." + vext;
            File source = new File("D:/video/" + vname + "." + vext);
            System.out.println("D:/video/" + vname + "." + vext);
            System.out.println("步骤3");
            int playback = 0;
            System.out.println("上传时间" + time);
            System.out.println("视频时长" + vtime);
            JSONObject obj = new JSONObject();
            obj.put("videosrc",videosrc);
            obj.put("200","成功");
            return obj.toString();
            // 重定向到查询所有用户的Controller，测试图片回显
        } else {
            JSONObject obj = new JSONObject();
            obj.put("400","失败");
            return obj.toString();
        }
    }
}
