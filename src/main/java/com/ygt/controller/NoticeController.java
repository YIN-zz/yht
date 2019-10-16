package com.ygt.controller;

import com.ygt.pojo.Notice;
import com.ygt.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping("addNotice")
    public String addNotice(Notice notice){

        return "";
    }
}
