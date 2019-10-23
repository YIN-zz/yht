package com.ygt.service;

import com.ygt.mapper.GoodsinfoDao;
import com.ygt.pojo.Goodsinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsinfoService {
    @Autowired
    private GoodsinfoDao goodsinfoDao;

    //根据备案证号添加出入库货物
    public int addBeiAn(Goodsinfo goodsinfo){
        return goodsinfoDao.addBeiAn(goodsinfo);
    }

}
