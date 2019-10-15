package com.ygt.service;

import com.ygt.mapper.Driveinfodao;
import com.ygt.pojo.Driveinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriveinfoService {

    @Autowired
    private Driveinfodao driveinfodao;


}
