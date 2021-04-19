﻿
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改用户信息</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="//resources/style.css" />">
</head>
<body>
<h1>修改用户信息</h1>
<form method="POST" , action="modifyUserInfo">

    全名：<br>
    <input type="text" name="fullName" value="${userEntity.fullName}"/><br/><br/>
    邮箱：<br>
    <input type="email" name="email" value="${userEntity.email}"/><br/><br/>
    电话：<br>
    <input type="text" name="phoneNo" value="${userEntity.phoneNo}"/><br/><br/>

    <input type="hidden" name="userId" value="${userEntity.id}"/>
    <input type="submit" value="提交"/>
</form>

</body>
</html>
