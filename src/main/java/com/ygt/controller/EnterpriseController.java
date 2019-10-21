package com.ygt.controller;

import com.ygt.pojo.Enterprise;
import com.ygt.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;

    //修改企业页面的信息
    public String addEnterprise(@RequestParam("files") MultipartFile[] multipartFiles, HttpServletRequest request, HttpSession session, Enterprise enterprise)throws IOException {
        Integer mid = Integer.parseInt((String) session.getAttribute("mid"));
        enterprise.setMid(mid);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String format = dateFormat.format(new Date());
        enterprise.setEtime(format);
        File file = new File(request.getSession().getServletContext().getRealPath("/upload"));
        if (!file.exists()){
            file.mkdir();
        }
        if(multipartFiles != null && multipartFiles.length > 0) {
            for (int i = 0; i < multipartFiles.length; i++) {
                if (!multipartFiles[i].getOriginalFilename().equals("")) {
                    String filename = multipartFiles[i].getOriginalFilename();
                    File file2 = new File(file, filename);
                    multipartFiles[i].transferTo(file2);
                    switch (i) {
                        case 0:
                            enterprise.setEpicture(filename);
                            break;
                        case 1:
                            enterprise.setEvideo(filename);
                            break;
                    }
                }
            }
        }
        enterpriseService.addEnterprise(enterprise);
        return "";
    }
}
