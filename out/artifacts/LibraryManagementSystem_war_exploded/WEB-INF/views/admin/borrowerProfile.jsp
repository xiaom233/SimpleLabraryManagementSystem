<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="MySimpleTag" uri="simpleTags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>图书馆管理系统</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/index.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/public.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/reset.css" />">
</head>
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
<h1 >借阅会员详细信息</h1>

<div class="account"><c:out value="账号：${userEntity.account}"/></div>
<div class="fullName"><c:out value="姓名：${userEntity.fullName}"/></div>
<div class="email"><c:out value="邮箱：${userEntity.email}"/></div>
<div class="phoneNo"><c:out value="电话：${userEntity.phoneNo}"/></div>

注册时间：<fmt:formatDate value="${userEntity.registerTime}" type="date"/>
<br><br>
<div class="status">账号状态：<MySimpleTag:showAccountStatus status="${userEntity.lock}"/></div>

<c:choose>
    <c:when test="${userEntity.lock}">
        <a href="<c:url value="/admin/lock?account=${userEntity.account}" />">解锁此账号</a>
    </c:when>
    <c:otherwise>
        <a href="<c:url value="/admin/lock?account=${userEntity.account}" />">锁定此账号</a>
    </c:otherwise>
</c:choose>


<a href="<c:url value="/admin/modifyUserInfo?account=${userEntity.account}" />">修改信息</a>


<h2>当前在借</h2>
<table border="1">
    <tr>
        <th>流水号</th>
        <th>书籍名称</th>
        <th>书籍代码</th>
        <th>借书状态</th>
        <th>借书时间</th>
        <th>应还时间</th>
    </tr>
    <c:forEach items="${records}" var="record" varStatus="i">
        <tr>
            <th>${record.id}</th>
            <th>${record.bookName}</th>
            <th>${record.bookCode}</th>
            <th><span style="color:green;font-weight:bold">在借</span></th>
            <th><fmt:formatDate value="${record.startTime}" type="date"/></th>
            <th><fmt:formatDate value="${record.endTime}" type="date"/></th>

        </tr>
    </c:forEach>
</table>

<h2>历史借书记录</h2>

<table border="1">
    <tr>
        <th>流水号</th>
        <th>书籍名称</th>
        <th>书籍代码</th>
        <th>借书状态</th>
        <th>借书时间</th>
        <th>还书时间</th>
    </tr>
    <c:forEach items="${historyRecords.items}" var="historyRecords">
        <tr>
            <th>${historyRecords.id}</th>
            <th>${historyRecords.bookName}</th>
            <th>${historyRecords.bookCode}</th>
            <th><span style="color:green">已还</span></th>
            <th><fmt:formatDate value="${historyRecords.startTime}" type="date"/></th>
            <th><fmt:formatDate value="${historyRecords.endTime}" type="date"/></th>

        </tr>
    </c:forEach>
</table>
每页${historyRecords.pageSize}个记录，
第${historyRecords.currentPageNo}/${historyRecords.totalPageCount}页,共${historyRecords.totalCount}个记录
<c:if test="${historyRecords.previousPage}">
    <a href="<c:url value="historyLendingList?pageNo=${historyRecords.currentPageNo-1}" />">上一页</a>
</c:if>
<c:if test="${historyRecords.nextPage}">
    <a href="<c:url value="historyLendingList?pageNo=${historyRecords.currentPageNo+1}" />">下一页</a>
</c:if>
</body>
</html>
