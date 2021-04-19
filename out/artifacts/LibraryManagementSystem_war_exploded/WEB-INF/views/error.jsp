<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/12/12
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发生了错误</title>
</head>
<body>

本次操作被拒绝,因为发生了异常<br/>
${message}<br/>

原因可能是:
1. 你绕过了提供的界面元素,使用url进行一些不安全的访问<br/>
2. 后端代码没有验证一些操作的错误性,并给出一些准确的引导信息<br/>
3. 前后端都没有给输入进行合法性判断<br/>
等<br/>
这些问题在往后系统不断修改更新得到解决<br/>
谢谢您的使用
</body>
</html>
