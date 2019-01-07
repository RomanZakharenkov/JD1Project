<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 07.09.2018
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.user.firstName} ${requestScope.user.lastName}</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/get-user" method="post">
        <h3>Информация о пользователе по ID</h3>
        <table>
            <tr>
                <td>
                    <p>Введите ID пользователя</p>
                </td>
                <td>
                    <input type="text" name="userId">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Показать" >
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
