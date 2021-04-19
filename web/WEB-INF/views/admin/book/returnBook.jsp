<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/12/11
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>还书</title>
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
<div id="nav">
    <!-- 导航栏版心部分 -->
    <div class="nav_con">
        <ul>
            <li><a href="../">首页</a></li>
            <li><a href="<c:url value="/book/bookList" />">图书馆书籍管理</a></li>
            <li><a href="<c:url value="/admin/checkRequests" />">借阅请求审核</a></li>
            <li><a href="<c:url value="#" />">还书管理</a></li>
            <li><a href="<c:url value="/admin/borrowerList" />">借阅者管理</a></li>
            <c:if test="${sessionScope.user.role eq 'root'}">
                <li><a href="<c:url value="/admin/adminList" />">管理员账户管理</a></li>
            </c:if>
        </ul>
    </div>
</div>

<form method="POST" , action="return">
    归还书籍编号：<br>
    <input type="text" name="bookCode"/><br/><br/>

    <input type="submit" value="提交"/>
</form>


<jsp:include page="../../footer.jsp"/>
</body>
</html>
