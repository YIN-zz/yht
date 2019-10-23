package com.ygt.service;

import com.ygt.mapper.BeiAnDao;
import com.ygt.pojo.BeiAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeiAnService {
    @Autowired
    private BeiAnDao beiAnDao;

    //根据备案证号添加出入库货物
    public int addBeiAn(BeiAn beiAn){
        return beiAnDao.addBeiAn(beiAn);
    }

}
