package com.ygt.controller;

import com.ygt.pojo.Firminfo;
import com.ygt.pojo.Userinfo;
import com.ygt.service.FirminfoService;
import com.ygt.service.UserinfoService;
import com.ygt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/easytubepass")
public class FirminfoController {

    @Autowired
    private FirminfoService firminfoService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private MD5Util md5Util;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    //企管信息注册
    @RequestMapping(value="enrollfirm",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enrollfirm(String firmname, String firmaddress, String firmprincipal, String firmphone, HttpSession session){
        Integer firmid = null;
        Integer userid = (Integer)session.getAttribute("userid");
        boolean enrollfirm = firminfoService.enrollfirm(firmid, firmname, firmaddress, firmprincipal, firmphone, userid);
        if(enrollfirm){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }

    //添加库管身份信息
    @RequestMapping(value="enrollerStorekeeper",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> enrollerStorekeeper(String username,String userphone,HttpSession session) throws NoSuchAlgorithmException {
        Integer userid = null;
        Integer useridentity = 3;
        Integer uservisible = 0;
        userinfoService.enrolluser(userid,username,userphone, md5Util.md5(new String ("123456")),useridentity,uservisible);
        Userinfo finduser = userinfoService.finduser(userphone);
        Integer userid1 = finduser.getUserid();
        Integer firmid = null;
        Integer userid2 = (Integer)session.getAttribute("userid");
        Firminfo findfirm = firminfoService.findfirm(userid2);
        String firmname = findfirm.getFirmname();
        String firmaddress = findfirm.getFirmaddress();
        String firmprincipal = findfirm.getFirmprincipal();
        String firmphone = findfirm.getFirmphone();
        boolean enrollfirm = firminfoService.enrollfirm(firmid, firmname, firmaddress, firmprincipal, firmphone, userid1);
        if(enrollfirm){
            result.put("msg","成功");
        }else{
            result.put("msg","服务器异常，请稍后重试");
        }
        return result;
    }
}
