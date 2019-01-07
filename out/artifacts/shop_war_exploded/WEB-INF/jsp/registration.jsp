<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 07.09.2018
  Time: 0:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body style="background-color: #cccccc">
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/registration" method="post"
      style="background-color: white; border-radius: 15px">
    <h2>Регистрация пользователя</h2>
    <h3>Введите Ваши данные</h3>
    <table>
        <tr>
            <td>
                <p>Имя</p>
            </td>
            <td>
                <p>
                    <input type="text" name="firstName">
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p>Фамилия</p>
            </td>
            <td>
                <p>
                    <input type="text" name="lastName">
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p>Номер телефона</p>
            </td>
            <td>
                <p>
                    <input type="text" name="number">
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p>Email</p>
            </td>
            <td>
                <p>
                    <input type="text" name="email">
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
                <p>Повторите пароль</p>
            </td>
            <td>
                <p>
                    <input type="password" name="passwordTwo">
                </p>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div align="center">
                    <input type="submit"
                           style="background-color: #aa0005; font-size: 30px; border-bottom-color: #aa0005; color: white; border-radius: 15px"
                           value="Зарегистрироваться">
                </div>

            </td>
        </tr>
    </table>
</form>
<%@ include file="footer.jsp" %>
</body>
</html>
