<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<html>
<head>
    <title>吐槽吧</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="//resources/style.css" />">
</head>
<body>
<h1>登录</h1>
<form method="POST">
    用户名: <input type="text" name="account" value="${sessionScope.account}"/><br/><br/>
    　密码: <input type="password" name="password"/><br/><br/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
