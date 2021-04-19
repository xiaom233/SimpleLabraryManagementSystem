﻿﻿﻿
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>书籍列表</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />">

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/index.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/public.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/reset.css" />">
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user.role eq 'borrower'}">
        <div id="nav">
            <!-- 导航栏版心部分 -->
            <div class="nav_con">
                <ul>
                    <li><a href="../">首页</a></li>
                    <li><a href="/book/bookList">书目查看</a></li>
                    <li><a href="<c:url value="/borrower/currentLendingList" />">当前在借</a></li>
                    <li><a href="<c:url value="/borrower/requestList" />">请求记录</a></li>
                    <li><a href="<c:url value="/borrower/historyLendingList" />">历史记录</a></li>
                </ul>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div id="nav">
            <!-- 导航栏版心部分 -->
            <div class="nav_con">
                <ul>
                    <li><a href="../admin/">首页</a></li>
                    <li class="margin-left"><a href="#">图书馆书籍管理</a></li>
                    <li><a href="<c:url value="/admin/checkRequests" />">借阅请求审核</a></li>
                    <li><a href="<c:url value="/admin/book/returnBook" />">还书管理</a></li>
                    <li><a href="<c:url value="/admin/borrowerList" />">借阅者管理</a></li>
                    <c:if test="${sessionScope.user.role eq 'root'}">
                        <li><a href="<c:url value="/admin/adminList" />">管理员账户管理</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<div id="h1test" class="BookList">

    <c:if test="${!(sessionScope.user.role eq 'borrower')}">
        <a href="<c:url value="/admin/book/addBook" />">添加新书</a>
    </c:if>

    <div class="case_con">
        <div class="case_box">
            <div>
                <c:forEach items="${paginationSupport.items}" var="bookEntity">
                    <dl>
                        <dt><img src="<c:url value="/resources/bookCover/1.png" />" alt=""></dt>
                        <dd><c:out value="书名：《${bookEntity.bookName}》"/></dd>
                        <dd><c:out value="作者：${bookEntity.author}"/></dd>
                        <dd><c:out value="出版社：${bookEntity.publisher}"/></dd>
                        <dd><c:out value="馆藏：${bookEntity.copyNumber}"/></dd>
                        <dd><a href="<c:url value="/book/bookInfo?bookId=${bookEntity.id}" />">查看详细</a>
                            <c:if test="${bookEntity.copyNumber eq bookEntity.remain}">
                                <a href="<c:url value="/admin/book/deleteBook?bookId=${bookEntity.id}" />">删除</a>
                            </c:if>
                        </dd>
                    </dl>
                </c:forEach>

            </div>

        </div>


    </div>
</div>
<div>
    每页${paginationSupport.pageSize}本，
    第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}本
    <c:if test="${paginationSupport.previousPage}">
        <a href="<c:url value="bookList?pageNo=${paginationSupport.currentPageNo-1}" />">上一页</a>
    </c:if>
    <c:if test="${paginationSupport.nextPage}">
        <a href="<c:url value="bookList?pageNo=${paginationSupport.currentPageNo+1}" />">下一页</a>
    </c:if>
</div>
<%--<%@ include file="../footer.jsp" %>--%>
<jsp:include page="../footer.jsp"/>
</body>
</html>