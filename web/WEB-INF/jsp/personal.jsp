<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 11.09.2018
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${sessionScope.currentUser.firstName} ${sessionScope.currentUser.lastName}</title>
</head>
<body style="background-color: #cccccc">
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/personal" method="post"
      style="border-radius: 15px; background-color: white">
    <table style="font-size: 20px">
        <tr>
            <td colspan="2">
                <p>
                    <fmt:message key="personal.info"/>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="personal.firstname"/>
            </td>
            <td>${sessionScope.currentUser.firstName}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="personal.lastname"/>
            </td>
            <td>${sessionScope.currentUser.lastName}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="personal.email"/>
            </td>
            <td>${sessionScope.currentUser.email}</td>
        </tr>
        <tr>
            <td>
                <fmt:message key="personal.number"/>
            </td>
            <td>${sessionScope.currentUser.number}</td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit"
                       style="background-color: #aa0005; font-size: 20px; border-bottom-color: #aa0005; color: white; border-radius: 15px"
                       value="<fmt:message key="personal.button.edit"/>">
            </td>
        </tr>
    </table>
</form>
<c:if test="${sessionScope.currentUser.role.name == 'ADMIN'}">
    <form style="border-radius: 15px; background-color: white">
        <table>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="Посмотреть всех пользователей" onclick="location.href='/all-users'"
                           style="background-color: #aa0005; font-size: 20px; border-bottom-color: #aa0005; color: white; border-radius: 15px">
                </td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="Обработать заказы" onclick="location.href='/process-orders'"
                           style="background-color: #aa0005; font-size: 20px; border-bottom-color: #aa0005; color: white; border-radius: 15px">
                </td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="Заказы" onclick="location.href='/reserved-orders'"
                           style="background-color: #aa0005; font-size: 20px; border-bottom-color: #aa0005; color: white; border-radius: 15px">
                </td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="Завершенные заказы" onclick="location.href='/finished-orders'"
                           style="background-color: #aa0005; font-size: 20px; border-bottom-color: #aa0005; color: white; border-radius: 15px">
                </td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="Добавить товар на склад" onclick="location.href='/add-count-product'"
                           style="background-color: #aa0005; font-size: 20px; border-bottom-color: #aa0005; color: white; border-radius: 15px">
                </td>
            </tr>
            <tr>
                <td></td>
            </tr>
        </table>
    </form>
</c:if>

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
            <td align="center" style="width: 33%; font-weight: bolder">У Вас пока нет заказов</td>
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
