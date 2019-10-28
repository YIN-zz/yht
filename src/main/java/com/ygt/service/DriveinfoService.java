package com.ygt.service;

import com.ygt.mapper.DriveinfoDao;
import com.ygt.pojo.Driveinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriveinfoService {

    @Autowired
    private DriveinfoDao driveinfodao;

    //司机注册
    public boolean adddriveinfo(String dphone,String dname,String dpassword){
        return driveinfodao.adddriveinfo(dphone,dname,dpassword);
    }

    //司机登录
    public Driveinfo finddriveinfo(String dphone,String dpassword){
        Driveinfo finddriveinfo = driveinfodao.finddriveinfo(dphone, dpassword);
        if(finddriveinfo!=null)
            return finddriveinfo;
        return null;
    }

    //司机修改密码
    public boolean updatedriveinfo(String dphone,String dpassword){
        return driveinfodao.updatedriveinfo(dphone, dpassword);
    }

    //司机查询个人信息
    public Driveinfo finddrive(String dphone){
        Driveinfo finddrive = driveinfodao.finddrive(dphone);
        return finddrive;
    }

    //根据司机手机号查询司机信息
    public Driveinfo findonedriveinfo(String dphone){
        Driveinfo driveinfo = driveinfodao.findonedriveinfo(dphone);
        return driveinfo;
    }

    //查询所有已经注册的司机
    public List<Driveinfo> findalldriveinfo(){
        List<Driveinfo> findalldriveinfo = driveinfodao.findalldriveinfo();
        return findalldriveinfo;
    }
}
