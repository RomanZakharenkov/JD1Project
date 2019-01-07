<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 21.09.2018
  Time: 3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить пользователя</title>
</head>
<body>
<body style="background-color: #cccccc">
<%@ include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/admin-change-user" method="post" style="background-color: white;border-radius: 15px" >
    <h3>Введите новые данные</h3>
    <table>
        <tr>
            <td>
                <p>Имя</p>
            </td>
            <td>
                <p>
                    <input type="text" name="firstName" value="${sessionScope.user.firstName}">
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p>Фамилия</p>
            </td>
            <td>
                <p>
                    <input type="text" name="lastName" value="${sessionScope.user.lastName}">
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p>Номер телефона</p>
            </td>
            <td>
                <p>
                    <input type="text" name="number" value="${sessionScope.user.number}">
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p>Email</p>
            </td>
            <td>
                <p>
                    <input type="text" name="email" value="${sessionScope.user.email}">
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p>Роль</p>
            </td>
            <td>
                <p>
                    <c:if test="${sessionScope.user.role.name == 'ADMIN'}">
                        <select name="role">
                            <option selected value="ADMIN">ADMIN</option>
                            <option value="USER">USER</option>
                        </select>
                    </c:if>
                    <c:if test="${sessionScope.user.role.name == 'USER'}">
                        <select name="role">
                            <option value="ADMIN">ADMIN</option>
                            <option selected value="USER">USER</option>
                        </select>
                    </c:if>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p>Пароль</p>
            </td>
            <td>
                <p>
                    <input type="password" name="password">
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p>Еще раз пароль</p>
            </td>
            <td>
                <p>
                    <input type="password" name="password2">
                </p>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div align="center" >
                    <input type="submit" style="background-color: #aa0005; font-size: 20px; border-bottom-color: #aa0005; color: white; border-radius: 15px" value="Применить">
                </div>
            </td>
        </tr>
    </table>
</form>
<%@ include file="footer.jsp"%>
</body>
</html>
