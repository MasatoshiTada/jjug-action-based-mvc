<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>エラー画面</title>
</head>
<body>
    <p><c:out value="${model.exceptionDto.message}"/></p>
    <a href="${pageContext.request.contextPath}/api/employee/index">入力画面へ</a>
</body>
</html>
