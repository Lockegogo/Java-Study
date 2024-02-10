<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<h1>登录页面</h1>
<hr>
<body>
<%--在 web-inf 下的所有页面或者资源，只能通过 controller 或者 servlet 进行访问--%>
<form action="${pageContext.request.contextPath}/user/login">
    用户名：<input type="text" name="username"> <br>
    密码： <input type="password" name="pwd"> <br>
    <input type="submit" value="提交">
</form>
</body>
</html>
