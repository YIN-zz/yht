package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Noticeinfo;
import com.ygt.service.NoticeinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/NoticeinfoController")
public class NoticeinfoController {
    @Autowired
    private NoticeinfoService noticeService;

    //警员增加通知信息
    @RequestMapping(value = "addNotice",produces = "application/json; charset=utf-8")
    public String addNotice(@RequestParam("files") MultipartFile[] multipartFiles, HttpServletRequest request, Noticeinfo notice, HttpSession session)throws IOException {
        Integer noticeid = (Integer) session.getAttribute("noticeid");
        notice.setNoticeid(noticeid);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 :hh:mm:ss");
        String format = dateFormat.format(new Date());
        notice.setNoticetime(format);
        // File file = new File(request.getSession().getServletContext().getRealPath("yht/src/main/WEB-INF/upload"));
        File file = new File("D:/upload");
        if (!file.exists()) {
            file.mkdir();
        }
        if (multipartFiles != null && multipartFiles.length > 0) {
            for (int i = 0; i < multipartFiles.length; i++) {
                if (!multipartFiles[i].getOriginalFilename().equals("")) {
                    String filename = multipartFiles[i].getOriginalFilename();
                    File file2 = new File(file, filename);
                    multipartFiles[i].transferTo(file2);
                    switch (i) {
                        case 0:
                            notice.setNoticepicture1(filename);
                            break;
                        case 1:
                            notice.setNoticepicture2(filename);
                            break;
                        case 2:
                            notice.setNoticepicture3(filename);
                            break;
                    }
                }
            }
        }
        int i = noticeService.addNotice(notice);
        JSONObject obj = new JSONObject();
        if (i > 0) {
            obj.put("200", "成功");
            return obj.toString();
        } else {
            obj.put("400", "失败");
            return obj.toString();
        }
    }
    //图片下载
    @RequestMapping(value = "filedownload",produces = "application/json; charset=utf-8")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request) throws Exception {
        ServletContext servletContext = request.getSession().getServletContext();
        String fileName = request.getParameter("filename");
        String realPath = servletContext.getRealPath("D:/upload" + fileName);
        InputStream inputStream = new FileInputStream(realPath);
        byte[] b = new byte[inputStream.available()];
        inputStream.read(b);
        fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=" + fileName);
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(b, httpHeaders, status);
        return responseEntity;
    }

    //资讯信息的接收
    @RequestMapping(value = "selectNotice",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String selectNotice(){
        List<Noticeinfo> noticeList = noticeService.selectNotice();
        JSONObject obj = new JSONObject();
        obj.put("noticeList",noticeList);
        obj.put("200","成功");
        return obj.toString();
    }
}
