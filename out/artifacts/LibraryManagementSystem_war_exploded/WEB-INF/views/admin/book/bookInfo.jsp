<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="MySimpleTag" uri="simpleTags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/12/8
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍详情</title>
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
<div id="h1test" class="BookList">
    <div id="nav">
        <!-- 导航栏版心部分 -->
        <div class="nav_con">
            <ul>
                <li><a href="../admin/">首页</a></li>
                <li><a href="../../book/bookList">图书馆书籍管理</a></li>
                <li><a href="<c:url value="/admin/checkRequests" />">借阅请求审核</a></li>
                <li><a href="<c:url value="/admin/book/returnBook" />">还书管理</a></li>
                <li><a href="<c:url value="/admin/borrowerList" />">借阅者管理</a></li>
                <c:if test="${sessionScope.user.role eq 'root'}">
                    <li><a href="<c:url value="/admin/adminList" />">管理员账户管理</a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <h1>书籍详情</h1>

    <div class="bookName"><c:out value="书名：《${bookEntity.bookName}》"/></div>
    <div class="fullName"><c:out value="作者：${bookEntity.author}"/></div>
    <div class="publisher"><c:out value="出版社：${bookEntity.publisher}"/></div>
    <div class="publisher"><c:out value="出版时间：${bookEntity.publishTime}"/></div>
    <div class="description"><c:out value="描述：${bookEntity.description}"/></div>
    <div class="copyNumber"><c:out value="馆藏：${bookEntity.copyNumber}"/></div>
    <div class="remain"><c:out value="在馆：${bookEntity.remain}"/></div>

    <%--                    <a href="<c:url value="/admin/deleteAdmin?adminId=${admin.id}" />">删除</a>--%>
    <%--            </c:if>--%>
    <a href="<c:url value="/admin/book/modifyBookInfo?bookId=${bookEntity.id}" />">修改信息</a>
    <a href="<c:url value="/admin/book/addBookItem?bookId=${bookEntity.id}" />">增加复本</a>

    <span id="h1test" class="BookItemList">
        <h1>书籍列表</h1>
        <table border="1" width="1000">
            <tr>
                <th>编号</th>
                <th>位置</th>
                <th>状态</th>
            </tr>

            <c:forEach items="${bookItem}" var="bookItem">
                <tr>
                    <th>${bookItem.bookCode}</th>
                    <th>${bookItem.location}</th>
                    <th><MySimpleTag:showStatus status="${bookItem.status}"/>
                    </th>
                    <th><a href="<c:url value="/admin/book/borrowingList?bookItemId=${bookItem.id}" />">借阅详情</a></th>
                    <th><a href="<c:url value="/admin/book/modifyBookItemInfo?bookItemId=${bookItem.id}" />">修改信息</a></th>
                    <th><c:if test="${bookItem.status}">
                        <a href="<c:url value="/admin/book/deleteBookItem?bookItemId=${bookItem.id}" />">删除</a>
                    </c:if></th>
                </tr>
            </c:forEach>

        </table>

    </span>

    <jsp:include page="../../footer.jsp"/>
</body>
</html>
