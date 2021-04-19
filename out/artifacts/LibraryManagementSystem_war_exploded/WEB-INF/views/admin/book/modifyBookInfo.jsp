﻿
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改书籍信息</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="//resources/style.css" />">
</head>
<body>
<h1>修改书籍信息</h1>
<form method="POST" , action="modifyBookInfo">

    书名：<br>
    <input type="text" name="bookName" value="${bookEntity.bookName}"/><br/><br/>
    作者：<br>
    <input type="text" name="author" value="${bookEntity.author}"/><br/><br/>
    出版社：<br>
    <input type="text" name="publisher" value="${bookEntity.publisher}"/><br/><br/>
    出版时间：<br>
    <input type="text" name="publishTime" value="${bookEntity.publishTime}"/><br/><br/>
    描述:<br>
    <textarea name="description" cols="80" rows="5">"${bookEntity.description}"</textarea><br/><br/>
<%--    <input type="text" name="description" value="${bookEntity.description}"/><br/><br/>--%>

    <input type="hidden" name="bookId" value="${bookEntity.id}"/>
    <input type="submit" value="提交"/>
</form>

</body>
</html>
