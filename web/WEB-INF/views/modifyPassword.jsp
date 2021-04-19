﻿
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<html>
<head>
    <title>修改信息</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="//resources/style.css" />">
</head>
<body>
<h1>修改信息

</h1>

<form method="POST">

    新密码: <input type="text" name="password" /><br/><br/>
    <input type="submit" value="确认修改"/>
</form>



</body>
</html>
