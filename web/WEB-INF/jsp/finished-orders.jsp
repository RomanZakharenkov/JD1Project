<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 21.09.2018
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Завершенные заказы</title>
</head>
<body style="background-color: #cccccc">
<%@ include file="header.jsp" %>
<c:if test="${requestScope.finishedOrders.size() > 0}">
    <table style="background-color: white; border-radius: 15px; font-size: 20px; width: 100%">
        <tr>
            <td align="center" style="width: 30%; font-weight: bolder">Номер заказа</td>
            <td align="center" style="width: 20%; font-weight: bolder">Дата заказа</td>
            <td align="center" style="width: 30%; font-weight: bolder">Статус заказа</td>
            <td align="center" style="width: 20%; font-weight: bolder"></td>
        </tr>
    </table>
</c:if>
<c:if test="${requestScope.finishedOrders.size() == 0}">
    <table style="background-color: white; border-radius: 15px; font-size: 20px; width: 100%">
        <tr>
            <td align="center" style="width: 33%; font-weight: bolder">Нет завершенных заказов</td>
        </tr>
    </table>
</c:if>
<br>
<c:forEach var="order" items="${requestScope.finishedOrders}">
    <table style="background-color: white; border-radius: 15px; font-size: 20px; width: 100%">
        <%--<form action="${pageContext.request.contextPath}/process-orders" method="post">--%>
            <tr>
                <td align="center" style="width: 30%">${order.order.id}</td>
                <td align="center" style="width: 20%">${order.order.date}</td>
                <td align="center" style="width: 30%">${order.order.status.name}</td>
                <td align="center" style="width: 20%">
                    <%--<input type="hidden" name="orderId" value="${order.order.id}">--%>
                    <%--<input type="submit" name="orderId" value="Выдать"--%>
                           <%--style="background-color: #aa0005; font-size: 15px; border-bottom-color: #aa0005; color: white; border-radius: 15px">--%>
                </td>
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
        <%--</form>--%>
    </table>
    <br>
</c:forEach>
<%@ include file="footer.jsp" %>
</body>
</html>
