package com.ygt.service;

import com.ygt.mapper.UserinfoDao;
import com.ygt.pojo.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserinfoService {

    @Autowired
    private UserinfoDao userinfoDao;

    //用户注册
    public boolean enrolluser(Integer userid,String username,String userphone,String userpassword,Integer useridentity,Integer uservisible){
        return userinfoDao.enrolluser(userid, username, userphone, userpassword, useridentity,uservisible);
    }
    //账号登录
    public Userinfo loginuser(String userphone,String userpassword){
        Userinfo loginuser = userinfoDao.loginuser(userphone, userpassword);
        if(loginuser!=null)
            return loginuser;
        return null;
    }
    //修改密码
    public boolean changeuser(String userphone,String userpassword){
        return userinfoDao.changeuser(userphone, userpassword);
    }
    //根据手机号判断是否已经注册
    public Userinfo finduser(String userphone){
        Userinfo finduser = userinfoDao.finduser(userphone);
        if(finduser!=null)
            return finduser;
        return null;
    }

    //根据userid查询名字
    public Userinfo findusername(Integer userid){
        Userinfo findusername = userinfoDao.findusername(userid);
        return findusername;
    }

    //修改头像
    public boolean updatephoto(Integer userid,String userphoto){
        return userinfoDao.updatephoto(userid,userphoto);
    }

    //查询企业注册人员
    public List<Userinfo> checkus(String firmname){
        List<Userinfo> checkus = userinfoDao.checkus(firmname);
        return checkus;
    }

    //删除企业注册人员
    public boolean remonveus(String username,String userphone){
        return userinfoDao.remonveus(username, userphone);
    }
}
