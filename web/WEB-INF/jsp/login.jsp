<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 16.09.2018
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>RZ Market</title>
</head>
<body style="background-color: #cccccc">
<%@ include file="header.jsp" %>
<br>
<h1 style="font-size: 300% " align="center">
    <fmt:message key="login.welcome"/>
</h1>
<form action="${pageContext.request.contextPath}/login" method="post">
    <table style="background-color: white;border-radius: 15px" align="center">
        <tr>
            <p></p>
        </tr>
        <tr>
            <td>
                <p>
                    <fmt:message key="login.email"/>
                </p>
            </td>
            <td>
                <input type="text" name="login" value="${param.login}">
            </td>
        </tr>
        <tr>
            <td>
                <p>
                    <fmt:message key="login.password"/>
                </p>
            </td>
            <td>
                <input type="password" name="password">
            </td>
        </tr>
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/registration">
                    <fmt:message key="login.registration"/>
                </a>
            </td>
            <td align="center">
                <input type="submit"
                       style="background-color: #aa0005; font-size: 20px; border-bottom-color: #aa0005; color: white; border-radius: 15px"
                       value="<fmt:message key="login.login"/>">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <a href="${pageContext.request.contextPath}/all-products">
                    <fmt:message key="login.next"/>
                </a>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <c:if test="${sessionScope.errorLogin == 'e'}">
                    <p>Неправильный логин или пароль.</p>
                    <p>Попробуйте еще раз</p>
                </c:if>
            </td>
        </tr>
    </table>
</form>
<%@ include file="footer.jsp" %>
</body>
</html>