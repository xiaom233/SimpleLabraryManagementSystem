
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>增添新书</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="//resources/style.css" />">
</head>
<body>
<h1>增添新书</h1>
<form method="POST" , action="addBook">

    ISBN：<br>
    <input type="text" name="isbn"/><br/><br/>
    书名：<br>
    <input type="text" name="bookName"/><br/><br/>
    作者：<br>
    <input type="text" name="author" /><br/><br/>
    出版社：<br>
    <input type="text" name="publisher" /><br/><br/>
    出版时间：<br>
    <input type="text" name="publishTime" /><br/><br/>
    描述:<br>
    <textarea name="description" cols="80" rows="5"></textarea><br/><br/>

    <input type="submit" value="提交"/>
</form>

</body>
</html>
