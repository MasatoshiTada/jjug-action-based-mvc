<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>結果画面</title>
</head>
<body>
    <c:choose>
        <c:when test="${empty model.employee}">
            <p>該当する社員はいませんでした。</p>
        </c:when>
        <c:otherwise>
            <table border="1">
                <tr><th>社員ID</th><th>氏名</th><th>入社年月日</th><th>部署ID</th><th>部署名</th></tr>
                <tr>
                    <td><c:out value="${model.employee.empId}"/></td>
                    <td><c:out value="${model.employee.name}"/></td>
                    <td><c:out value="${model.employee.joinedDate}"/></td>
                    <td><c:out value="${model.employee.department.deptId}"/></td>
                    <td><c:out value="${model.employee.department.name}"/></td>
                </tr>
            </table>
        </c:otherwise>
    </c:choose>
    <a href="./index">入力画面へ</a>
</body>
</html>
