﻿﻿
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书馆管理系统</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />">

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/index.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/public.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/reset.css" />">
    <style>
        footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            line-height: 40px
        }
    </style>
</head>
<body>

<c:if test="${not empty sessionScope.user && not empty sessionScope.user.id }">
    <div id="nav">
        <!-- 导航栏版心部分 -->
        <div class="nav_con">
            <ul>
                <li class="margin-left"><a href="#">首页</a></li>
                <li><a href="<c:url value="/book/bookList" />">书目查看</a></li>
                <li><a href="<c:url value="/borrower/currentLendingList" />">当前在借</a></li>
                <li><a href="<c:url value="/borrower/requestList" />" >请求记录</a></li>
                <li><a href="<c:url value="/borrower/historyLendingList" />" >历史记录</a></li>
            </ul>
        </div>
    </div>

    <a href="<c:url value="/logout" />">注销</a>
    <jsp:include page="footer.jsp"/>
</c:if>



</body>
</html>
