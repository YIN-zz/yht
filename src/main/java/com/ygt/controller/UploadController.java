package com.ygt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ygt.util.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UploadController {

	//上传方法
	@Autowired
	private UploadService uploadService;

	//上传文件(上传单个文件）
	//表单上传的格式（enctype="multipart/form-data" 上传方式为 method="post" 表单文件类型 input type="file"）
	@RequestMapping(value="uploadfile",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String upload(MultipartFile file) throws IllegalStateException, IOException {
		//System.out.println(file);
		String path="F:/项目上传文件存放位置/";
		String str1 = uploadService.uploadone(file, path);
		//System.out.println(str1);
		str1="http://192.168.3.10:8080/项目上传文件存放位置/"+str1;
		//System.out.println(str1);
		JSONObject obj = new JSONObject();
		obj.put("msg", str1);
		String str = obj.toString();
		//System.out.println(str);s
		return str;
	}

	//上传多个文件(表单里面填写的是myfiles，次处是数组传值要注意）
	//哪里发送的请求带有上传文件哪里使用下面的代码
	@RequestMapping("/filesUpload")
	@ResponseBody
	//requestParam要写才知道是前台的那个数组
	public List<String> filesUpload(@RequestParam("myfiles") MultipartFile[] files,
									HttpServletRequest request) {
		List<String> list = new ArrayList<String>();
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// 保存文件
				list = uploadService.saveFile(request, file, list);
			}
		}
		//写着测试，删了就可以
		for (int i = 0; i < list.size(); i++) {
			System.out.println("成功上传文件：" + list.get(i));
		}
		//*return "index";*//	/跳转的页面
		return list;
	}
}
