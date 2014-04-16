<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1><c:out value="${message}"/></h1>
<form action="/test_ssh/user/updateUser" name="userForm" method="post">
    <input type="hidden" name="id" value="${user.id }">
    姓名：<input type="text" name="userName" value="${user.userName }">
    年龄：<input type="text" name="age" value="${user.age }">
    <input type="submit" value="编辑" >
</form>
</body>
</html>