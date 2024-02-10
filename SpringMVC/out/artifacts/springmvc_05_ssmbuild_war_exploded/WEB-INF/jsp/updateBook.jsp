<%--
  Created by IntelliJ IDEA.
  User: Lockegogo
  Date: 2023/1/22
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改信息</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
        <%--出现问题：我们提交了修改的 SQL 请求，但是修改失败，初次考虑是事务问题，但配置完事务后依旧失败--%>
        <%--看一下 SQL 语句能否执行成功，SQL 执行失败，修改未完成--%>
        <%--前端传递隐藏域--%>
        书籍名称：<input type="text" name="bookName" value="${book.bookName}" required/><br><br><br>
        书籍数量：<input type="text" name="bookCounts" value="${book.bookCounts}" required/><br><br><br>
        书籍详情：<input type="text" name="detail" value="${book.detail}" required/><br><br><br>
        <input type="submit" value="提交"/>
        <input type="hidden" name="bookID" value="${book.bookID}"/><br><br><br>
    </form>
</div>
