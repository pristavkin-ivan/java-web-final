<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customTags" prefix="ctg"%>

<c:import url="../static/localization.jsp"/>

<fmt:setBundle basename="pagecontent" var="rb"/>


<html>

<head>
    <title><fmt:message key="title.home" bundle="${rb}"/> </title>
</head>

<body>

<c:import url="../static/header.jsp" />

<main>

    <p><h2><fmt:message key="text.welcome" bundle="${ rb }"/> <ctg:get-login/>!</h2></p>

</main>

<c:import url="../static/footer.jsp" />

</body>

</html>
