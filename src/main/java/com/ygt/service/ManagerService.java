package com.ygt.service;

import com.ygt.mapper.ManagerinfoDao;
import com.ygt.pojo.Managerinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private ManagerinfoDao managerinfodao;

    //企管注册
    public boolean addmanagerinfo(String mphone,String mname,String mpassword,String mrelease,String maddress,String mposition){
        return managerinfodao.addmanager(mphone, mname, mpassword, mrelease, maddress, mposition);
    }

    //企管登录
    public Managerinfo findmanager(String mphone,String mpassword){
        Managerinfo findmanager = managerinfodao.findmanager(mphone, mpassword);
        return findmanager;
    }

    //企管修改密码
    public boolean changemanager(String mphone,String mpassword){
        return managerinfodao.updatemanager(mphone,mpassword);
    }

    //企管查询个人信息
    public Managerinfo findmanager(String mphone){
        Managerinfo findmanagerinfo = managerinfodao.findmanagerinfo(mphone);
        return findmanagerinfo;
    }

    //企管查询（按手机号）
    public Managerinfo findonemanager(String mphone) {
        Managerinfo findonemanager = managerinfodao.findonemanager(mphone);
        return findonemanager;
    }

    //查询所有注册的企业信息
    public List<Managerinfo> findallmanager(){
        List<Managerinfo> managerinfos = managerinfodao.findallmanager();
        return managerinfos;
    }
}
