<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 10.09.2018
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${requestScope.product.brand} ${requestScope.product.model}</title>
</head>
<body style="background-color: #cccccc">
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/productInfo" method="post">
    <table style="background-color: white; border-radius: 15px">
        <tr>
            <td style="width: 50%" align="center">
                <input type="image" src="${requestScope.product.image}" style="width: 70%">
            </td>
            <td>
                <p>
                    <fmt:message key="product.info"/>: ${requestScope.product.brand} ${requestScope.product.model}
                </p>
                <p>
                    <fmt:message key="product.category"/>: ${requestScope.product.category.name}
                </p>
                <p>
                    <fmt:message key="product.brand"/>: ${requestScope.product.brand}
                </p>
                <p>
                    <fmt:message key="product.model"/>: ${requestScope.product.model}
                </p>
                <br>
                <p>
                    <fmt:message key="product.description"/>
                </p>
                <p>
                    ${requestScope.product.description}
                </p>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center" style="font-size: 26px">
                <p>
                    <fmt:message key="product.price"/>
                </p>
                <p>
                    ${requestScope.product.price} <fmt:message key="product.typeprice"/>
                </p>
            </td>
        </tr>
        <%--<tr>--%>
        <%--<td>Модель</td>--%>
        <%--<td>${requestScope.product.brand} ${requestScope.product.model}</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td>Категория</td>--%>
        <%--<td>${requestScope.product.category.name}</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td>Цена</td>--%>
        <%--<td>${requestScope.product.price}</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td>Описание</td>--%>
        <%--<td>${requestScope.product.description}</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td colspan="2">--%>
        <%--<input type="image" src="${requestScope.product.image}" width=20%>--%>
        <%--</td>--%>
        <%--</tr>--%>
    </table>
</form>
<%@ include file="footer.jsp" %>
</body>
</html>
