<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lockegogo
  Date: 2023/1/16
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="<c:url value="/user"/>" method="post">
            <label>
                <input type="text" name="method">
            </label>
            <input type="submit">
        </form>
    </body>
</html>
