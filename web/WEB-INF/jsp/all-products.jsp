<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 10.09.2018
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>RZ market</title>
</head>
<body style="background-color: #cccccc">
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/all-products" method="post">
    <table style="background-color: white; border-radius: 15px; width: 100%">
        <tr>
            <td colspan="2" align="right">Категория</td>
            <td align="right">
                <c:if test="${sessionScope.filter.tv != '1'}">
                    <input type="checkbox" name="tv" value="1"> Телевизоры
                </c:if>
                <c:if test="${sessionScope.filter.tv == '1'}">
                    <input type="checkbox" name="tv" value="1" checked> Телевизоры
                </c:if>
            </td>
            <td align="left">
                <c:if test="${sessionScope.filter.audio != '2'}">
                    <input type="checkbox" name="audio" value="2"> Акустика
                </c:if>
                <c:if test="${sessionScope.filter.audio == '2'}">
                    <input type="checkbox" name="audio" value="2" checked> Акустика
                </c:if>
            </td>
            <td colspan="3"></td>
        </tr>
        <tr>
            <c:if test="${sessionScope.filter.sort == 'desc'}">
                <td colspan="3" align="right">
                    <input type="radio" name="rad" value="desc" checked>Сортировать по убыванию цены
                </td>
                <td colspan="3" align="left">
                    <input type="radio" name="rad" value="asc">Сортировать по возрастанию цены
                </td>
            </c:if>
            <c:if test="${sessionScope.filter.sort == 'asc'}">
                <td colspan="3" align="right">
                    <input type="radio" name="rad" value="desc">Сортировать по убыванию цены
                </td>
                <td colspan="3" align="left">
                    <input type="radio" name="rad" value="asc" checked>Сортировать по возрастанию цены
                </td>
            </c:if>
        </tr>
        <tr>
            <td>Минимальная цена</td>
            <td>
                <c:if test="${sessionScope.filter.minPrice == ''}">
                    <input type="text" name="minPrice">
                </c:if>
                <c:if test="${sessionScope.filter.minPrice != ''}">
                    <input type="text" name="minPrice" value="${sessionScope.filter.minPrice}">
                </c:if>
            </td>
            <td>Максимальная цена</td>
            <td>
                <c:if test="${sessionScope.filter.maxPrice == ''}">
                    <input type="text" name="maxPrice">
                </c:if>
                <c:if test="${sessionScope.filter.maxPrice != ''}">
                    <input type="text" name="maxPrice" value="${sessionScope.filter.maxPrice}">
                </c:if>
            </td>
            <td>
                <input type="hidden" name="form" value="filterForm">
                <input type="submit"
                       style="background-color: #aa0005; font-size: 15px; border-bottom-color: #aa0005; color: white; border-radius: 15px"
                       name="form"
                       value="Применить">
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/clearfilter" style="color: black">Сбросить фильтр</a>
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td colspan="5">--%>
                <%--<a href="report/PriceList.txt" download>Скачать Прайслист</a>--%>
            <%--</td>--%>
        <%--</tr>--%>
    </table>
</form>
<c:forEach var="product" items="${requestScope.products}">
    <form action="${pageContext.request.contextPath}/all-products" method="post">

        <table style="border-radius: 15px; background-color: white">
            <tr>
                <td style="width: 40%" align="center">
                    <input type="image" src="${product.image}" width="70%">
                </td>
                <td style="width: 40%; font-size: 18px">
                    <a href="${pageContext.request.contextPath}/productInfo?id=${product.id}"
                       style="font-size: 32px">${product.brand} ${product.model}</a><br>
                    <p>
                        <fmt:message key="allproducts.description"/>
                    </p>
                    <p>${product.description}</p>
                </td>
                <td style="width: 20%; font-size: 32px" align="center">
                    <p>
                        <fmt:message key="allproducts.price"/>
                    </p>
                    <p>
                            ${product.price} <fmt:message key="allproducts.typeprice"/>
                    </p>
                    <p>
                        <c:if test="${sessionScope.currentUser != null}">
                            <input type="hidden" name="productId" value="${product.id}"/>
                            <input type="hidden" name="form" value="basket"/>
                            <input type="submit"
                                   style="background-color: #aa0005; font-size: 30px; border-bottom-color: #aa0005; color: white; border-radius: 15px"
                                   value="В корзину">
                        </c:if>
                    </p>
                </td>
            </tr>
        </table>

    </form>
</c:forEach>
<%--<table style="border-radius: 15px; background-color: white; width: 100% " >--%>
    <%--<tr></tr>--%>
    <%--<tr>--%>
        <%--<td align="center">--%>
            <%--<a href="report/PriceList.txt" download>Скачать Прайслист</a>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <%--<tr></tr>--%>
<%--</table>--%>
<%--<br>--%>

<%@ include file="footer.jsp" %>
</body>
</html>
