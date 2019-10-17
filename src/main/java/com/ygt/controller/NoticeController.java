package com.ygt.controller;

import com.ygt.pojo.Notice;
import com.ygt.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    //警员增加通知信息
    @RequestMapping("addNotice")
    public String addNotice(Notice notice, HttpSession session){
        Integer pid = (Integer) session.getAttribute("pid");
        notice.setPid(pid);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String format = dateFormat.format(new Date());
        notice.setNtime(format);
        noticeService.addNotice(notice);
        return "index";
    }

    //资讯信息的接收
    @RequestMapping("selectNotice")
    public String selectNotice(Model model){
        List<Notice> noticeList = noticeService.selectNotice();
        model.addAttribute("list",noticeList);
        return "index";
    }
}
