<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 17.09.2018
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Корзина</title>
</head>
<body style="background-color: #cccccc">
<%@ include file="header.jsp" %>
<c:if test="${sessionScope.items.size() == 0}">
    <table style="border-radius: 15px; background-color: white; width: 100%; font-size: 30px">
        <tr>
            <td style="width: 60%" align="center">В корзине нет товаров</td>
        </tr>
        <tr>
            <td style="width: 60%" align="center">
                <a href="${pageContext.request.contextPath}/all-products">Перейти к выбору товара</a>
            </td>
        </tr>
    </table>
    <br>
</c:if>
<c:if test="${sessionScope.items.size() > 0}">


    <table style="border-radius: 15px; background-color: white; width: 100%; font-size: 30px">
        <tr>
            <td style="width: 60%" align="center">Товар</td>
            <td style="width: 10%" align="center">Количество</td>
            <td style="width: 30%" align="center">Цена</td>
        </tr>
    </table>
    <br>
    <c:forEach var="item" items="${sessionScope.order}">
        <form style="border-radius: 15px; background-color: white">
            <table style="width: 100%; font-size: 30px">
                <tr>
                    <td style="width: 20%" align="center">
                        <input type="image" src="${item.product.image}" width="70%">
                    </td>
                    <td style="width: 40%" align="center">${item.product.brand} ${item.product.model}</td>
                    <td style="width: 10%" align="center">${item.count} ед.</td>
                    <td style="width: 30%" align="center">${item.product.price * item.count} руб.</td>
                </tr>
            </table>
        </form>
    </c:forEach>
    <form action="${pageContext.request.contextPath}/basket" method="post">
        <table style="border-radius: 15px; background-color: white; width: 100%; font-size: 30px">
            <tr>
                <td style="width: 50%" align="center">Итоговая сумма</td>
                <td style="width: 20%" align="center">${sessionScope.summ} руб.</td>
                <td style="width: 30%" align="center">
                    <input type="submit" value="Оформить заказ"
                           style="background-color: #aa0005; font-size: 30px; border-bottom-color: #aa0005; color: white; border-radius: 15px"/>
                </td>
            </tr>
        </table>
    </form>
    <c:if test="${sessionScope.notProduct == 'error'}">
        <table style="border-radius: 15px; background-color: white; width: 100%; font-size: 30px">
            <tr>
                <td>
                    <h1>Товара в данном количестве нет на складе</h1>
                </td>
            </tr>
        </table>
    </c:if>
    <br>
</c:if>
<%@ include file="footer.jsp" %>
</body>
</html>
