<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/10/18
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="addmoments" enctype="multipart/form-data" method="post">
    姓名：<input type="hidden" value="张三" name="momentsname"><br>
    手机号：<input type="hidden" value="13119179239" name="momentsphone"><br>
    发布内容：<input type="text" name="momentstext"><br>
    <input type="file" name="myfiles"><br>
    <input type="file" name="myfiles"><br>
    <input type="file" name="myfiles"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
