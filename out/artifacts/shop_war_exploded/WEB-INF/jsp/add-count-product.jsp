<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 21.09.2018
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить товар на склад</title>
</head>
<body style="background-color: #cccccc">
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/add-count-product" method="post"
      style="border-radius: 15px; background-color: white">
    <table>
        <tr>
            <td colspan="2">Выберите номенклатуру</td>
        </tr>
        <tr>
            <td>Товар</td>
            <td>
                <select name="product-nomen">
                    <c:forEach var="nomen" items="${requestScope.allNomen}">
                        <option value="${nomen.id}">${nomen.brand} ${nomen.model}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Количество</td>
            <td>
                <input type="number" name="count">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit"
                       style="background-color: #aa0005; font-size: 20px; border-bottom-color: #aa0005; color: white; border-radius: 15px"
                       value="Добавить на склад">
            </td>
        </tr>
        <c:if test="${param.check == 'true'}">
            <tr>
                <td colspan="2" align="center">
                    <p>Товар добавлен на склад</p>
                </td>
            </tr>
        </c:if>
    </table>
</form>

<%@ include file="footer.jsp" %>
</body>
</html>
