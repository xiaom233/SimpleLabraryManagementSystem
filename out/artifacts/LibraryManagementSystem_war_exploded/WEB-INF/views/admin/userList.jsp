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
    <h1>全部用户</h1>
    <ul class="spitterList">
        <c:forEach items="${paginationSupport.items}" var="spitter">
            <li id="spitter_<c:out value="${spitter.id}"/>">
                <div class="spitterUserName"><c:out value="用户名: ${spitter.userName}"/></div>
                <div class="spitterFullName"><c:out value="姓名:  ${spitter.firstName},${spitter.lastName}"/></div>
                <div class="spitterEmail"><c:out value="邮箱:  ${spitter.email}"/></div>
            </li>

        </c:forEach>
    </ul>
</div>
每页${paginationSupport.pageSize}个用户，
第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}个用户
<c:if test="${paginationSupport.previousPage}">
    <a href="<c:url value="/userList?pageNo=${paginationSupport.currentPageNo-1}" />">上一页</a>
</c:if>
<c:if test="${paginationSupport.nextPage}">
    <a href="<c:url value="/userList?pageNo=${paginationSupport.currentPageNo+1}" />">下一页</a>
</c:if>
</body>
</html>