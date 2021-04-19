﻿
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<html>
<head>
    <title>吐槽吧</title>
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

<c:choose>
    <c:when test="${not empty sessionScope.user && !(sessionScope.user.role eq 'borrower') }">
        <div id="nav">
            <!-- 导航栏版心部分 -->
            <div class="nav_con">
                <ul>
                    <li><a href="#">首页</a></li>
                    <li><a href="<c:url value="/book/bookList" />">图书馆书籍管理</a></li>
                    <li><a href="<c:url value="/admin/checkRequests" />">借阅请求审核</a></li>
                    <li><a href="<c:url value="/admin/book/returnBook" />">还书管理</a></li>
                    <li><a href="<c:url value="/admin/borrowerList" />">借阅者管理</a></li>
                    <c:if test="${sessionScope.user.role eq 'root'}">
                        <li><a href="<c:url value="/admin/adminList" />">管理员账户管理</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
        <a href="<c:url value="/admin/modifyPassword" />">修改密码</a>
        <a href="<c:url value="/logout" />">注销</a>

    </c:when>
    <c:otherwise>
        <a href="<c:url value="/" />">管理员登录</a>
    </c:otherwise>
</c:choose>

<jsp:include page="../footer.jsp"/>
</body>
</html>
