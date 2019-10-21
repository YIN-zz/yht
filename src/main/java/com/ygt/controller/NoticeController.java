package com.ygt.controller;

import com.ygt.pojo.Notice;
import com.ygt.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    //警员增加通知信息
    @RequestMapping("addNotice")
    public String addNotice(@RequestParam("files") MultipartFile[] multipartFiles, HttpServletRequest request, Notice notice, HttpSession session)throws IOException {
        Integer pid = (Integer) session.getAttribute("pid");
        notice.setPid(pid);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String format = dateFormat.format(new Date());
        notice.setNtime(format);
        // File file = new File(request.getSession().getServletContext().getRealPath("yht/src/main/WEB-INF/upload"));
        File file = new File("D:/upload");
        if (!file.exists()){
            file.mkdir();
        }
        if(multipartFiles != null && multipartFiles.length > 0) {
            for (int i = 0; i < multipartFiles.length; i++) {
                if (!multipartFiles[i].getOriginalFilename().equals("")) {
                    String filename = multipartFiles[i].getOriginalFilename();
                    File file2 = new File(file, filename);
                    multipartFiles[i].transferTo(file2);
                    switch (i) {
                        case 0:
                            notice.setNvideo(filename);
                            break;
                        case 1:
                            notice.setNpicture(filename);
                            break;
                    }
                }
            }
        }
        noticeService.addNotice(notice);
        return "index";
    }
    //图片下载
    @RequestMapping(value = "filedownload")
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
    @RequestMapping("selectNotice")
    public String selectNotice(Model model){
        List<Notice> noticeList = noticeService.selectNotice();
        model.addAttribute("list",noticeList);
        return "selectn";
    }
}
