<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customTags" prefix="ctg"%>

<c:import url="localization.jsp"/>

<fmt:setBundle basename="pagecontent" var="rb"/>


<html>

<style>
    <c:import url="${pageContext.request.contextPath}/WEB-INF/css/mainPage.css"/>
</style>

<head>
    <title><fmt:message key="title.home" bundle="${rb}"/> </title>
</head>

<body>

<c:import url="header.jsp" />

<main>

    <p><h2><fmt:message key="text.welcome" bundle="${ rb }"/> <ctg:get-login/>!</h2></p>

    <div>
        <h2>
            <fmt:message key="info.fitnessCenter" bundle="${rb}"/>
            <fmt:message key="info.fitnessCenter" bundle="${rb}"/>
            <fmt:message key="info.fitnessCenter" bundle="${rb}"/>
            <fmt:message key="info.fitnessCenter" bundle="${rb}"/>
        </h2>
    </div>

</main>

<c:import url="footer.jsp" />

</body>

</html>
