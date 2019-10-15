package com.ygt.service;

import com.ygt.common.utils.MD5Util;
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
    public boolean addpoliceinfo(Integer pphone,String pname,String paddress,String ppassword){
        return policeinfodao.addpoliceinfo(pphone,pname,paddress,ppassword);
    }

    //警员登录
    public Policeinfo findonepolice(Integer pphone,String ppassword){
        System.out.println(ppassword);
        Policeinfo findonepolice = policeinfodao.findonepolice(pphone, ppassword);
        return findonepolice;
    }

    //警员修改密码
    public boolean updatepolice(Integer pphone,String ppassword){
        return policeinfodao.updatepolice(pphone, ppassword);
    }

    //查询所有警员信息
    public List<Policeinfo> findallpolice(){
        List<Policeinfo> list = policeinfodao.findallPolice();
        return list;
    }
}
