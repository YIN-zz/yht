<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/21
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
</head>
<body>
<a href="/listPage">回到首页</a><br/>
<form method="post" action="addNotice" enctype="multipart/form-data">
    发布通告消息：<input type="text" name="nmessage"/><br/>
    发布文字:<input type="text" name="ntext"/><br/>
    发布照片:<input type="file" name="files"/><br/>
    发布视频:<input type="file" name="files"/><br/>

    <input type="submit" value="提交"/>
</form>
</body>
</body>
</html>
