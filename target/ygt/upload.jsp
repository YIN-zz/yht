<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/10/17
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>上传图片</title>
</head>
<body>
<form action="filesUpload" method="POST" name="xiangmu" id="xiangmu"
      enctype="multipart/form-data">
    <table>
        <tr>
            <td>身份证图片上传：</td>
            <td><input type=file name="myfiles" id="doc"
                       onchange="showImage();">
            </td>
            <div id="localImag">
                <img id="preview" width=-1 height=-1 style="diplay:none" />
            </div>
        </tr>
        <tr>
            <td>公司运营情况:</td>
            <!-- 为了实现张图片上传，上传框这个name要都一样 -->
            <td><input type="file" name="myfiles" id="doc1"></td>
            <td>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="提交" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
