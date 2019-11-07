package com.ygt.service;

import com.ygt.mapper.ReleaseinfoDao;
import com.ygt.pojo.Releaseinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseinfoService {

    @Autowired
    private ReleaseinfoDao releaseinfoDao;

    //添加发布朋友圈的信息
    public boolean enrollrelease(Integer releaseid,String releasename,String releasecontent,String  releasefirst,String releasesecond,String releasethird,String releasetime){
       return releaseinfoDao.enrollrelease(releaseid, releasename, releasecontent, releasefirst, releasesecond, releasethird, releasetime);
    }

    //查看所有的朋友圈发布信息
    public List<Releaseinfo> findallrelease(){
        List<Releaseinfo> findallrelease = releaseinfoDao.findallrelease();
        return findallrelease;
    }

    //查看自己发布的朋友圈信息
    public List<Releaseinfo> findrelease(String releasename){
        List<Releaseinfo> findrelease = releaseinfoDao.findrelease(releasename);
        return findrelease;
    }

    //查看单个朋友圈信息
    public List<Releaseinfo> findonerelease(String releasename,String releasetime){
        List<Releaseinfo> findonerelease = releaseinfoDao.findonerelease(releasename, releasetime);
        return  findonerelease;
    }

    //删除朋友圈信息
    public boolean removerelease(String releasename,String releasetime){
        return releaseinfoDao.removerelease(releasename,releasetime);
    }
}
