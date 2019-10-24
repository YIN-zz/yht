package com.ygt.controller;

import com.ygt.pojo.Goodsinfo;
import com.ygt.service.GoodsinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GoodsinfoController {

    @Autowired
    private GoodsinfoService goodsinfoService;

    //查询出入库货物的信息
    @RequestMapping("findgoodsinfo")
    @ResponseBody
    public List<Goodsinfo> findgoodsinfo(Integer drid){
        List<Goodsinfo> findgoodsinfo = goodsinfoService.findgoodsinfo(drid);
        return findgoodsinfo;
    }
}
