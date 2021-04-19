<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="MySimpleTag" uri="simpleTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="<c:url value="."/>" target="">返回</a>

<h1>借阅车</h1>

<table border=\"1\">
    <tr>
        <th>书名</th>
        <th>编号</th>
        <th>位置</th>
    </tr>

    <MySimpleTag:showBorrowingCart>
        <tr>
            <th>《${name}》</th>
            <th>${bookItem.bookCode}</th>
            <th>${bookItem.location}</th>
        </tr>
    </MySimpleTag:showBorrowingCart>


</table>

<c:if test="${not empty sessionScope.item0}">
    <a href="<c:url value="/borrower/commitRequest" />">提交借阅请求</a>
</c:if>
</body>
</html>
