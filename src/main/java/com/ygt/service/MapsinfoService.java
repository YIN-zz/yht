package com.ygt.service;

import com.ygt.mapper.MapsinfoDao;
import com.ygt.pojo.Mapsinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapsinfoService {

    @Autowired
    private MapsinfoDao mapsinfoDao;

    //根据司机的driverid添加地址信息
    public boolean enrollmap(Integer mapsid,String mapslongitude,String mapslatitude,Integer driverid){
        return mapsinfoDao.enrollmap(mapsid, mapslongitude, mapslatitude, driverid);
    }

    //根据司机的driverid去修改位置信息
    public boolean changemap(String mapslongitude,String mapslatitude,Integer driverid){
        return mapsinfoDao.changemap(mapslongitude, mapslatitude, driverid);
    }

    //根据司机的driverid去查看位置信息
    public Mapsinfo findmap(Integer driverid){
        Mapsinfo findmap = mapsinfoDao.findmap(driverid);
        return findmap;
    }
}
