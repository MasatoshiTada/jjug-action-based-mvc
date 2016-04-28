<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>入力画面</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <p>社員IDを入力してください(77, 88, 99で例外発生)</p>

    <%-- 検証エラーメッセージの表示 --%>
    <c:if test="${not empty errors}">
        <ul class="error">
        <c:forEach items="${errors}" var="error">
            <li><c:out value="${error}"/></li>
        </c:forEach>
        </ul>
    </c:if>

    <form action="./result" method="get">
        社員ID：<input type="text" name="id">
        <input type="submit" value="検索">
    </form>
</body>
</html>
