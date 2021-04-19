<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
    <title>Title</title>
</head>
<body>
<div class="bookName"><c:out value="书名：${bookEntity.bookName}"/></div>
<div class="author"><c:out value="作者：${bookEntity.author}"/></div>
<div class="bookCode"><c:out value="编号：${bookItem.bookCode}"/></div>
<div class="location"><c:out value="位置：${bookItem.location}"/></div>

<h3>当前借书用户</h3>

<c:choose>
    <c:when test="${not empty current.startTime}">
        <table border="1">
            <tr>
                <th>流水号</th>
                <th>借书人账号</th>
                <th>借书时间</th>
                <th>应还时间</th>
            </tr>
            <tr>
                <th>${current.id}</th>
                <th>${current.borrowerAccount}</th>
                <th>${current.startTime}</th>
                <th>${current.endTime}</th>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        当前本书未借出！
    </c:otherwise>
</c:choose>


<div id="h1test" class="ManagerList">
    <h1>借阅记录</h1>
    <table border="1">
        <tr>
            <th>流水号</th>
            <th>借书人账号</th>
            <th>借书时间</th>
            <th>还书时间</th>
        </tr>

        <c:forEach items="${records.items}" var="record">
            <tr>
                <th>${record.id}</th>
                <th>${record.borrowerAccount}</th>
                <th>${record.startTime}</th>
                <th>${record.endTime}</th>
            </tr>
        </c:forEach>
    </table>
</div>
每页${records.pageSize}条记录，
第${records.currentPageNo}/${records.totalPageCount}页,共${records.totalCount}条记录
<c:if test="${records.previousPage}">
    <a href="<c:url value="borrowingList?pageNo=${records.currentPageNo-1}&bookItemId=${bookItem.id}" />">上一页</a>
</c:if>
<c:if test="${records.nextPage}">
    <a href="<c:url value="borrowingList?pageNo=${records.currentPageNo+1}&bookItemId=${bookItem.id}" />">下一页</a>
</c:if>
</body>
</html>
