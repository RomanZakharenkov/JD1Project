<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 06.09.2018
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Все пользователи</title>
</head>
<body style="background-color: #cccccc">
<%@ include file="header.jsp" %>
<table style="background-color: white; border-radius: 15px; font-size: 20px; width: 100%" >
    <tr>
        <td align="center" style="width: 30%">
            <a>Имя Фамилия</a>
        </td>
        <td align="center" style="width: 10%">ID</td>
        <td align="center" style="width: 10%">Роль</td>
        <td align="center" style="width: 30%">Email</td>
        <td align="center" style="width: 20%">Номер телефона</td>
    </tr>
</table>
<br>
<c:forEach var="user" items="${sessionScope.users}">
    <table style="background-color: white; border-radius: 15px; font-size: 20px; width: 100%">
        <tr>
            <td align="center" style="width: 30%">
                <a href="${pageContext.request.contextPath}/user-info?id=${user.id}">${user.firstName} ${user.lastName}</a>
            </td>
            <td align="center" style="width: 10%">${user.id}</td>
            <td align="center" style="width: 10%">${user.role.name}</td>
            <td align="center" style="width: 30%">${user.email}</td>
            <td align="center" style="width: 20%">${user.number}</td>
        </tr>
    </table>
    <br>
</c:forEach>
<br>
<%@ include file="footer.jsp" %>
</body>
</html>
