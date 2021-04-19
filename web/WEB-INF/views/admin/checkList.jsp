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
    <title>吐槽吧</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="//resources/style.css" />">
</head>
<body>

<a href="<c:url value="/manager/" />">返回首页</a>
<div id="h1test" class="spittleList">
    <h1>全部吐槽</h1>
    <ul class="spittleList">
        <c:forEach items="${paginationSupport.items}" var="spittle">
                    <li id="spittle_<c:out value="${spittle.id}"/>">
                        <div class="spittleMessage"><c:out value="${spittle.message}"/></div>
                        <div class="spittleTime">
                            <fmt:formatDate value="${spittle.postedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            by <c:out value="${spittle.spitter.userName }"/>
                            <a href="<c:url value="/manager/check?spittleId=${spittle.id}" />">通过</a>
                            <a href="<c:url value="/manager/delete?spittleId=${spittle.id}" />">删除</a>
                        </div>
                    </li>
            <%--            <c:if test="${spittle.ischecked}">--%>
            <%--                <li id="spittle_<c:out value="${spittle.id}"/>">--%>
            <%--                    <div class="spittleMessage"><c:out value="${spittle.message}"/></div>--%>
            <%--                    <div class="spittleTime">--%>
            <%--                        <fmt:formatDate value="${spittle.postedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
            <%--                        by <c:out value="${spittle.spitter.userName }"/>--%>
            <%--                    </div>--%>
            <%--                    <a href="<c:url value="/spittles/delete?spittleId=${spittle.id}" />">删除</a>--%>
            <%--                </li>--%>
            <%--            </c:if>--%>
            <%--            <c:otherwise>--%>
            <%--                <li id="spittle_<c:out value="${spittle.id}"/>">--%>
            <%--                    <div class="spittleMessage"><c:out value="${spittle.message}"/></div>--%>
            <%--                    <div class="spittleTime">--%>
            <%--                        <fmt:formatDate value="${spittle.postedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
            <%--                        by <c:out value="${spittle.spitter.userName }"/>--%>
            <%--                        <a href="<c:url value="/spittles/check?spittleId=${spittle.id}" />">通过</a>--%>
            <%--                        <a href="<c:url value="/spittles/delete?spittleId=${spittle.id}" />">删除</a>--%>
            <%--                    </div>--%>
            <%--                </li>--%>
            <%--            </c:otherwise>--%>
        </c:forEach>
    </ul>
</div>
每页${paginationSupport.pageSize}条吐槽，
第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}条吐槽
<c:if test="${paginationSupport.previousPage}">
    <a href="<c:url value="/checkList?pageNo=${paginationSupport.currentPageNo-1}" />">上一页</a>
</c:if>
<c:if test="${paginationSupport.nextPage}">
    <a href="<c:url value="/checkList?pageNo=${paginationSupport.currentPageNo+1}" />">下一页</a>
</c:if>
</body>
</html>