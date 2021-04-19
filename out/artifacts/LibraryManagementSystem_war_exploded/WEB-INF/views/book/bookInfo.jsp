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
    <h1>书籍详情</h1>
    <%--            <c:if test="${manager.delete=='0'}">--%>

    <div class="bookName"><c:out value="书名：《${bookEntity.bookName}》"/></div>
    <div class="isbn"><c:out value="ISBN：${bookEntity.isbn}"/></div>
    <div class="fullName"><c:out value="作者：${bookEntity.author}"/></div>
    <div class="publisher"><c:out value="出版社：${bookEntity.publisher}"/></div>
    <div class="publisher"><c:out value="出版时间：${bookEntity.publishTime}"/></div>
    <div class="description"><c:out value="描述：${bookEntity.description}"/></div>
    <div class="copyNumber"><c:out value="馆藏：${bookEntity.copyNumber}"/></div>
    <div class="remain"><c:out value="在馆：${bookEntity.remain}"/></div>


    <a href="<c:url value="/borrower/borrowingCart" />" target="_blank">查看借阅车</a>
    <span id="h1test" class="BookItemList">
        <h1>书籍列表</h1>
        <table border="1">

            <tr>
                <th>编号</th>
                <th>位置</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${bookItem}" var="bookItem" varStatus="i">

                <tr>
                    <th><c:out value="${bookItem.bookCode}"/></th>
                    <th><c:out value="${bookItem.location}"/></th>

                    <c:choose>
                        <c:when test="${status[i.index]}">
                            <th><span style="color: blue">审查中</span></th>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${bookItem.status}">
                                <th><span style="color: green">在馆</span></th>
                                <th><a href="<c:url value="/borrower/addIntoCart?bookItemId=${bookItem.id}" />">加入借阅车</a></th>
                            </c:if>

                            <c:if test="${!bookItem.status}">
                                <th><span style="color: red">借出</span></th>
                            </c:if>
                        </c:otherwise>
                    </c:choose>

                </tr>

            </c:forEach>
       </table>
    </span>

<jsp:include page="../footer.jsp"/>
</body>
</html>
