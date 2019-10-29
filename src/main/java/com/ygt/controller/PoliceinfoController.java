package com.ygt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ygt.pojo.Userinfo;
import com.ygt.service.PoliceinfoService;
import com.ygt.service.UserinfoService;
import com.ygt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;

@Controller
public class PoliceinfoController {

    @Autowired
    private PoliceinfoService policeinfoService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private MD5Util md5Util;

    //添加其余警员的注册
    @RequestMapping(value="enrollpolice",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String enrollpolice(String username,String userphone,String policeaddress) throws NoSuchAlgorithmException {
        Integer userid = null;
        Integer useridentity = 0;
        userinfoService.enrolluser(userid,username, userphone, md5Util.md5(new String ("123456")),useridentity);
        Userinfo finduser = userinfoService.finduser(userphone);
        Integer userid1 = finduser.getUserid();
        Integer policeid = null;
        policeinfoService.enrollpolice(policeid,policeaddress,userid1);
        JSONObject obj = new JSONObject();
        obj.put("200","成功");
        return obj.toString();
    }
}
