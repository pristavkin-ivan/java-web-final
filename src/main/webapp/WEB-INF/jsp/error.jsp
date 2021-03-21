<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="static/localization.jsp"/>

<fmt:setBundle basename="pagecontent" var="rb"/>

<html>
<head>
    <style>
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/error.css"/>
    </style>
    <title><fmt:message key="label.error" bundle="${rb}"/> </title>
</head>

<body>

<a href="${pageContext.request.contextPath}/go"><fmt:message key="title.home" bundle="${rb}"/></a>

<main>
    <h2 style="color: red; text-align: center">
        <c:choose>

            <c:when test="${not empty param.msg}">
                <fmt:message key="${param.msg}" bundle="${rb}"/>
            </c:when>

            <c:otherwise>
                <fmt:message key="error.unknown" bundle="${rb}"/>
            </c:otherwise>

        </c:choose>
    </h2>

    <img style="margin-left: 510px; margin-top: 30px" src="https://thumbs.dreamstime.com/b/икона-ошибки-16125237.jpg"/>

</main>

</body>

<c:import url="static/footer.jsp" />

</html>
