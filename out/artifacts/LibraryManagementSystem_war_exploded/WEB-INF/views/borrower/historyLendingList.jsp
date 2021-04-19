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
            <li class="margin-left"><a href="../">首页</a></li>
            <li><a href="<c:url value="/book/bookList" />">书目查看</a></li>
            <li><a href="<c:url value="/borrower/currentLendingList" />">当前在借</a></li>
            <li><a href="<c:url value="/borrower/requestList" />" >请求记录</a></li>
            <li><a href="<c:url value="/borrower/historyLendingList" />" >历史记录</a></li>
        </ul>
    </div>
</div>
<h3>历史借书记录</h3>

<table border="1">
    <tr>
        <th>流水号</th>
        <th>书籍名称</th>
        <th>书籍代码</th>
        <th>借书状态</th>
        <th>借书时间</th>
        <th>还书时间</th>
    </tr>
    <c:forEach items="${paginationSupport.items}" var="record">
        <tr>
            <th>${record.id}</th>
            <th>${record.bookName}</th>
            <th>${record.bookCode}</th>
            <th><span style="color:green">已还</span></th>
            <th><fmt:formatDate value="${record.startTime}" type="date"/></th>
            <th><fmt:formatDate value="${record.endTime}" type="date"/></th>

        </tr>
    </c:forEach>
</table>
每页${paginationSupport.pageSize}个记录，
第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}个记录
<c:if test="${paginationSupport.previousPage}">
    <a href="<c:url value="historyLendingList?pageNo=${paginationSupport.currentPageNo-1}" />">上一页</a>
</c:if>
<c:if test="${paginationSupport.nextPage}">
    <a href="<c:url value="historyLendingList?pageNo=${paginationSupport.currentPageNo+1}" />">下一页</a>
</c:if>

<jsp:include page="../footer.jsp"/>
</body>
</html>
