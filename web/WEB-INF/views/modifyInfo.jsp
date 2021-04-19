﻿﻿
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
<c:choose>
    <c:when test="${not empty sessionScope.spitter && not empty sessionScope.spitter.id }">
        <form method="POST">
            姓:   <input type="text" name="lastName" value="${sessionScope.spitter.lastName}"/><br/><br/>
            名:   <input type="text" name="firstName" value="${sessionScope.spitter.firstName}"/><br/><br/>
            邮箱: <input type="text" name="email" value="${sessionScope.spitter.email}"/><br/><br/>
            <input type="submit" value="修改"/>
        </form>
    </c:when>
    <c:otherwise>
        <form method="POST">
            全名: <input type="text" name="fullName" value="${sessionScope.manager.fullName}"/><br/><br/>
            邮箱: <input type="text" name="email" value="${sessionScope.manager.email}"/><br/><br/>
            电话: <input type="text" name="phoneNo" value="${sessionScope.manager.phoneNo}"/><br/><br/>
            <input type="submit" value="修改"/>
        </form>
    </c:otherwise>
</c:choose>


</body>
</html>
