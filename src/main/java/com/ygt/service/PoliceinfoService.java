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

    public boolean addpoliceinfo(Integer pphone,String pname,String paddress,String ppassword){
        System.out.println(pphone+pname+paddress+ppassword);
        return policeinfodao.addpoliceinfo(pphone,pname,paddress,ppassword);
    }

    public List<Policeinfo> findallpolice(){
        List<Policeinfo> list = policeinfodao.findallPolice();
        return list;
    }
}
