<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/12/9
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>审查请求</title>
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
            <li><a href="<c:url value="/admin/book/returnBook" />">还书管理</a></li>
            <li><a href="<c:url value="/admin/borrowerList" />">借阅者管理</a></li>
            <c:if test="${sessionScope.user.role eq 'root'}">
                <li><a href="<c:url value="/admin/adminList" />">管理员账户管理</a></li>
            </c:if>
        </ul>
    </div>
</div>


<h3>当前待审查请求</h3>

<table border="1">
    <tr>
        <th>流水号</th>
        <th>借书人账号</th>
        <th>书籍名称</th>
        <th>书籍编号</th>
        <th>请求状态</th>
        <th>请求时间</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${paginationSupport.items}" var="request" varStatus="i">
        <tr>
            <th>${request.id}</th>
            <th>${request.borrowerAccount}</th>
            <th>${request.bookName}</th>
            <th>${request.bookCode}</th>
            <th><span style="color:red;font-weight:bold">未审查</span></th>
            <th><fmt:formatDate value="${request.startTime}" type="both"/></th>
            <th><a href="<c:url value="check?requestId=${request.id}"/>">通过</a></th>
            <th><a href="<c:url value="deny?requestId=${request.id}"/>">拒绝</a></th>
        </tr>
    </c:forEach>
</table>


每页${paginationSupport.pageSize}条请求，
第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}条请求
<c:if test="${paginationSupport.previousPage}">
    <a href="<c:url value="checkRequests?pageNo=${paginationSupport.currentPageNo-1}" />">上一页</a>
</c:if>
<c:if test="${paginationSupport.nextPage}">
    <a href="<c:url value="checkRequests?pageNo=${paginationSupport.currentPageNo+1}" />">下一页</a>
</c:if>


<jsp:include page="../footer.jsp"/>
</body>
</html>
