package com.ygt.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

//上传方法
@Service
public class UploadService {

	//表单上传的格式（enctype="multipart/form-data" 上传方式为 method="post" 表单文件类型 input type="file"）
	//此处单个上传文件使用了UUID加识别码
	public String uploadone(MultipartFile file,String path1) throws IllegalStateException, IOException {
		//String path = req.getServletContext().getRealPath("/file/");
		//将上传文件保存的路径
		String path=path1;
		//获得上传文件的文件名
		String filename = file.getOriginalFilename();
		//System.out.println(filename);
		//对上传文件的文件名字进行重新命名（UUID生成识别码）
		String name=UUID.randomUUID().toString().replace("-", "")+"-"+filename;
		//上传文件保存的完整路径
		//System.out.println(path+name);
		File newfile = new File(path+name);
		//返回保存的文件名字
		String str=name;
		//System.out.println(str);
		file.transferTo(newfile);
		return str;
	}


	//上传多个文件
	//保存文件原来的名字没有使用UUID生成识别码
	public List<String> saveFile(HttpServletRequest request,
								  MultipartFile file, List<String> list) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                /*String filePath = request.getSession().getServletContext()
                        .getRealPath("/")
                        + "upload/" + file.getOriginalFilename();*/
                //上传文件名字生成UUID识别码
				/*String filename = file.getOriginalFilename();
				String name=UUID.randomUUID().toString().replace("-", "")+"-"+filename;
				String filePath = "F:/项目上传文件存放位置/" + name;*/
				//上传文件名字使用文件本来的名字
                String filename = file.getOriginalFilename();
                String name=UUID.randomUUID().toString().replace("-", "")+"-"+filename;
				String filePath = "F:/项目上传文件存放位置/" + name;
				list.add(name);
				File saveDir = new File(filePath);
				if (!saveDir.getParentFile().exists())
					saveDir.getParentFile().mkdirs();
				// 转存文件
				file.transferTo(saveDir);
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
