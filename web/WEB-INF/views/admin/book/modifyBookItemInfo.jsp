﻿
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改书籍复本信息</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="//resources/style.css" />">
</head>
<body>
<h1>修改书籍复本信息</h1>
<form method="POST" , action="modifyBookItemInfo">

    书籍位置：<br>
    <input type="text" name="location" value="${bookItem.location}"/><br/><br/>

    <input type="hidden" name="bookItemId" value="${bookItem.id}"/>
    <input type="submit" value="提交"/>
</form>

</body>
</html>
