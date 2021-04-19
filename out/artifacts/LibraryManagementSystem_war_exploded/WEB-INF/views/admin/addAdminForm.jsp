﻿
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>添加管理员</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="//resources/style.css" />">
</head>
<body>
<h1>注册新管理员</h1>
<form method="POST", action="addAdmin">

    全名：  <input type="text" name="fullName" /><br/><br/>
    邮箱：  <input type="email" name="email" /><br/><br/>
    电话：  <input type="text" name="phoneNo" /><br/><br/>

    <input type="submit" value="注册" />
</form>

</body>
</html>
