package com.ygt.controller;

import com.ygt.pojo.Driveinfo;
import com.ygt.service.DriveinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DriveController {

    @Autowired
    private DriveinfoService driveinfoService;


}
