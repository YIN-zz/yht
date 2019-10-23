<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/21
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
</head>
<body>
<a href="/listPage">回到首页</a><br/>
<%--<span th:text="${list.nmessage}"></span><br/>
<span th:if="${list.nvideo == null}">
        图片1：无
    </span><br/>
<span th:if="${list.nvideo != null}">
        图片1：<a th:href="${'/filedownload?filename='+list.nvideo}"><img th:src="${'D:/upload'+list.nvideo}" height="300px" width="300px"/></a>
    </span><br/>
<span th:if="${list.npicture == null}">
        图片2：无
    </span><br/>
<span th:if="${list.npicture != null}">
       图片2:<a th:href="${'/filedownload?filename='+list.npicture}"><img th:src="${'D:/upload'+list.npicture}" height="300px" width="300px"/></a>
    </span><br/>--%>

<%--<table >
    <tr>
        <th>通知消息</th>
        <th>文字内容内容</th>
        <th>视频</th>
        <th>图片</th>
    </tr>
    <c:forEach items="${list}" var="v">
        <tr >
            <td >${v.nmessage}</td>
            <td >${v.ntext}</td>
            <td>
                <a th:href="${'/filedownload?filename='+list.nvideo}"><img th:src="${'D:/upload'+list.nvideo}" height="300px" width="300px"/></a>
            </td>
            <td>
                <a th:href="${'/filedownload?filename='+list.npicture}"><img th:src="${'D:/upload'+list.npicture}" height="300px" width="300px"/></a>
            </td>
        </tr>
    </c:forEach>
</table>--%>

<table >
    <tr>
        <th>通知消息</th>
        <th>文字内容内容</th>
        <th>视频</th>
        <th>图片</th>
    </tr>
    <c:forEach items="${list}" var="v">
        <tr >
            <td >${v.nmessage}</td>
            <td >${v.ntext}</td>
            <td>
                <img src="D:/upload/128E347B5CF560000adf2.bmp" height="300px" width="300px"/>
             <%--   <c:if test="${list.nvideo == null}">  图片1：无</c:if>
                <c:if test="${list.nvideo != null}">--%>
                   <%-- <a href="${'/filedownload?filename='+list.nvideo}"><img src="${'D:/upload'+list.nvideo}" height="300px" width="300px"/></a>--%>
           <%-- </c:if>--%>
            </td>
            <td>
               <%-- <c:if test="${list.npicture == null}">  图片2：无</c:if>
                <c:if test="${list.npicture != null}">--%>
                  <%--  <a href="${'/filedownload?filename='+list.npicture}"><img src="${'D:/upload'+list.npicture}" height="300px" width="300px"/></a>--%>
              <%--  </c:if>--%>
            </td>
        </tr>
    </c:forEach>
</table>
</body>

</html>
