<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/12/7
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>图书馆管理系统</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/index.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/public.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/reset.css" />">
</head>
<body>
<h1>管理员详情页面</h1>

<div class="account"><c:out value="账号：${userEntity.account}"/></div>
<div class="fullName"><c:out value="姓名：${userEntity.fullName}"/></div>
<div class="email"><c:out value="邮箱：${userEntity.email}"/></div>
<div class="phoneNo"><c:out value="电话：${userEntity.phoneNo}"/></div>

注册时间：<fmt:formatDate value="${userEntity.registerTime}" type="date"/>
<br><br>
<div class="status"><c:out value="账号状态：${userEntity.lock}"/></div>
<a href="<c:url value="/admin/lock?account=${userEntity.account}" />">删除此管理员</a>
<a href="<c:url value="/admin/modifyUserInfo?account=${userEntity.account}" />">修改信息</a>


</body>
</html>
