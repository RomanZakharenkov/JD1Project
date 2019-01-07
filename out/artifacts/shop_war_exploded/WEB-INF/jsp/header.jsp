<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 06.09.2018
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="locale.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form>
    <div style="background-color: #aa0005; border-radius: 15px">
        <table style="width: 100%;">
            <tr>
                <td style="width: 30%">
                    <input type="image" src="img/RZ%20MY.png" style="width: 10%" align="left">
                    <h1 style="color: white; font-size: 36px" align="left" >
                        <fmt:message key="head.name"/>
                    </h1>
                </td>
                <td style="width: 30%">
                    <a href="${pageContext.request.contextPath}/all-products" style="color: white">
                        <fmt:message key="head.allproduct"/>
                    </a>
                </td>
                <td style="width: 10%" align="center">
                    <a href="${pageContext.request.contextPath}/change-lang?lang=ru_RU" role="link"
                       style="color: white">
                        <fmt:message key="head.lang.ru"/>
                    </a>
                    <br>
                    <a href="${pageContext.request.contextPath}/change-lang?lang=en_US" role="link"
                       style="color: white">
                        <fmt:message key="head.lang.en"/>
                    </a>
                </td>
                <td style="width: 20%" align="center">
                    <c:if test="${sessionScope.currentUser.role == null}">
                        <a href="${pageContext.request.contextPath}/login" role="link" style="color: white">
                            <fmt:message key="head.login.false"/>
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.currentUser.role != null}">
                        <a href="${pageContext.request.contextPath}/personal" role="link" style="color: white">
                            <fmt:message
                                    key="head.login.true"/> ${sessionScope.currentUser.firstName} ${sessionScope.currentUser.lastName}
                        </a>
                        <br>
                        <a href="${pageContext.request.contextPath}/logout" role="link" style="color: white">
                            <fmt:message key="head.login.logout"/>
                        </a>
                    </c:if>
                </td>
                <td style="width: 20%" align="center">
                    <c:if test="${sessionScope.currentUser.role != null}">
                        <a href="${pageContext.request.contextPath}/basket" role="link" style="color: white">
                            <fmt:message key="head.basket"/> (${sessionScope.count} ед)
                        </a><br>
                        <a href="/clearbasket" role="line" style="color: white">
                            <fmt:message key="head.basket.clear"/>
                        </a>
                    </c:if>
                </td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
