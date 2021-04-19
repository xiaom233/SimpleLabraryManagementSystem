﻿﻿
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>管理员列表</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/index.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/public.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/reset.css" />">
</head>
<body>

<div id="nav">
    <!-- 导航栏版心部分 -->
    <div class="nav_con">
        <ul>
            <li><a href="../admin/">首页</a></li>
            <li><a href="../book/bookList">图书馆书籍管理</a></li>
            <li><a href="<c:url value="/admin/checkRequests" />">借阅请求审核</a></li>
            <li><a href="<c:url value="/admin/book/returnBook" />">还书管理</a></li>
            <li><a href="<c:url value="/admin/borrowerList" />">借阅者管理</a></li>
            <c:if test="${sessionScope.user.role eq 'root'}">
                <li><a href="<c:url value="/admin/adminList" />">管理员账户管理</a></li>
            </c:if>
        </ul>
    </div>
</div>

<a href="<c:url value="/admin/addAdmin" />">增加管理员</a>
<div id="h1test" class="ManagerList">
    <h1>管理员列表</h1>
    <div class="case_con">
        <div class="case_box">
            <div>
                <c:forEach items="${paginationSupport.items}" var="borrower">
                    <dl>
                        <dd><c:out value="账号：${borrower.account}"/></dd>
                        <dd><c:out value="姓名：${borrower.fullName}"/></dd>
                        <dd><c:out value="邮箱：${borrower.email}"/></dd>
                        <dd><c:out value="电话：${borrower.phoneNo}"/></dd>
                        <dd><a href="<c:url value="/admin/adminProfile?account=${borrower.account}" />"
                               target="_blank">查看详细</a>
                        </dd>
                    </dl>
                </c:forEach>
            </div>


</div>
    </div>
    每页${paginationSupport.pageSize}个管理员，
    第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}个管理员
    <c:if test="${paginationSupport.previousPage}">
        <a href="<c:url value="adminList?pageNo=${paginationSupport.currentPageNo-1}" />">上一页</a>
    </c:if>
    <c:if test="${paginationSupport.nextPage}">
        <a href="<c:url value="adminList?pageNo=${paginationSupport.currentPageNo+1}" />">下一页</a>
    </c:if>

</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>