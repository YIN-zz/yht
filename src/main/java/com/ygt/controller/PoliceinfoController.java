package com.ygt.controller;

import com.ygt.pojo.Userinfo;
import com.ygt.service.PoliceinfoService;
import com.ygt.service.UserinfoService;
import com.ygt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/easytubepass")
public class PoliceinfoController {

    @Autowired
    private PoliceinfoService policeinfoService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private MD5Util md5Util;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //添加其余警员的注册
    @RequestMapping(value="enrollpolice",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enrollpolice(String username,String userphone,String policeaddress) throws NoSuchAlgorithmException {
        Integer userid = null;
        Integer useridentity = 1;
        Integer uservisible = 0;
        userinfoService.enrolluser(userid,username, userphone, md5Util.md5(new String ("123456")),useridentity,uservisible);
        Userinfo finduser = userinfoService.finduser(userphone);
        Integer userid1 = finduser.getUserid();
        Integer policeid = null;
        boolean enrollpolice = policeinfoService.enrollpolice(policeid, policeaddress, userid1);
        if(enrollpolice){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }
}
