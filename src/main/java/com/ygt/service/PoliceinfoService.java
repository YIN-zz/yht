package com.ygt.service;

import com.ygt.mapper.Policeinfodao;
import com.ygt.pojo.Policeinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliceinfoService {

    @Autowired
    private Policeinfodao policeinfodao;

    //警员注册
    public boolean addpoliceinfo(String pphone,String pname,String paddress,String ppassword){
        return policeinfodao.addpoliceinfo(pphone,pname,paddress,ppassword);
    }

    //警员登录
    public Policeinfo findonepolice(String pphone,String ppassword){
        Policeinfo findonepolice = policeinfodao.findonepolice(pphone, ppassword);
        return findonepolice;
    }

    //警员修改密码
    public boolean updatepolice(String pphone,String ppassword){
        return policeinfodao.updatepolice(pphone, ppassword);
    }

    //警员查看个人信息
    public Policeinfo findpolices(String pphone){
        Policeinfo findpolices = policeinfodao.findpolices(pphone);
        return findpolices;
    }

    //查询警员信息（根据手机号）
    public Policeinfo findpolice(String pphone){
        Policeinfo findpolice = policeinfodao.findpolice(pphone);
        return findpolice;
    }

    //查询所有警员信息
    public List<Policeinfo> findallpolice(){
        List<Policeinfo> list = policeinfodao.findallPolice();
        return list;
    }
}
