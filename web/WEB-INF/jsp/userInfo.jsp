<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 07.09.2018
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${requestScope.user.firstName} ${requestScope.user.lastName}</title>
</head>
<body style="background-color: #cccccc">
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/user-info" method="post"
      style="border-radius: 15px; background-color: white">
    <h3>Информация о пользователе</h3>
    <table style="font-size: 20px">
        <tr>
            <td>Имя</td>
            <td>${sessionScope.user.firstName}</td>
        </tr>
        <tr>
            <td>Фамилия</td>
            <td>${sessionScope.user.lastName}</td>
        </tr>
        <tr>
            <td>Email</td>
            <td>${sessionScope.user.email}</td>
        </tr>
        <tr>
            <td>Номер телефона</td>
            <td>${sessionScope.user.number}</td>
        </tr>
        <tr>
            <td>ID</td>
            <td>${sessionScope.user.id}</td>
        </tr>
        <tr>
            <td>Статус</td>
            <td>${sessionScope.user.role.name}</td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Редактировать"
                       style="background-color: #aa0005; font-size: 20px; border-bottom-color: #aa0005; color: white; border-radius: 15px">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="${pageContext.request.contextPath}/all-users">Посмотреть другого пользователя</a>
            </td>
        </tr>
    </table>
</form>
<c:if test="${requestScope.orders.size() > 0}">
    <table style="background-color: white; border-radius: 15px; font-size: 20px; width: 100%">
        <tr>
            <td align="center" style="width: 33%; font-weight: bolder">Номер заказа</td>
            <td align="center" style="width: 33%; font-weight: bolder">Дата заказа</td>
            <td align="center" style="width: 33%; font-weight: bolder">Статус заказа</td>
        </tr>
    </table>
</c:if>
<c:if test="${requestScope.orders.size() == 0}">
    <table style="background-color: white; border-radius: 15px; font-size: 20px; width: 100%">
        <tr>
            <td align="center" style="width: 33%; font-weight: bolder">У пользователя пока нет заказов</td>
        </tr>
    </table>
</c:if>

<br>
<c:forEach var="order" items="${requestScope.orders}">
    <table style="background-color: white; border-radius: 15px; font-size: 20px; width: 100%">
        <tr>
            <td align="center" style="width: 33%">${order.order.id}</td>
            <td align="center" style="width: 33%">${order.order.date}</td>
            <td align="center" style="width: 33%">${order.order.status.name}</td>
        </tr>
        <tr>
            <td align="center" style="width: 20%">Товар</td>
            <td align="center" style="width: 20%">Цена</td>
            <td align="center" style="width: 20%">Количество</td>
            <td align="center" style="width: 30%">Сумма</td>
        </tr>
        <c:forEach var="item" items="${order.lineItems}">
            <tr>
                <td align="center" style="width: 20%">${item.product.brand} ${item.product.model}</td>
                <td align="center" style="width: 20%">${item.product.price} руб.</td>
                <td align="center" style="width: 20%">${item.count} ед.</td>
                <td align="center" style="width: 40%">${item.product.price * item.count} руб.</td>
            </tr>
        </c:forEach>
    </table>
    <br>
</c:forEach>

<%@ include file="footer.jsp" %>
</body>
</html>
