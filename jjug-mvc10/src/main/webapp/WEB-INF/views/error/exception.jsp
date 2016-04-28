<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>エラー画面</title>
</head>
<body>
    <p><c:out value="${exceptionDto.message}"/></p>
    <a href="${mvc.basePath}/employee/index">入力画面へ</a>
</body>
</html>
