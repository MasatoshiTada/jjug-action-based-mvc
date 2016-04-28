<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>エラー画面</title>
</head>
<body>
    <p><c:out value="${message}"/></p>
    <a href="${pageContext.request.contextPath}/api/employee/index">入力画面へ</a>
</body>
</html>
