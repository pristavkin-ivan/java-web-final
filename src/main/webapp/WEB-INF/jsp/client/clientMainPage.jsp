<%--
  Created by IntelliJ IDEA.
  User: Ivan
  Date: 02.03.2021
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="en_US" scope="session"/>
<fmt:setBundle basename="pagecontent_en_us" var="rb"/>

<html>

<head>
    <title><fmt:message key="title.home" bundle="${rb}"/> </title>
</head>

<body>

<c:import url="../static/clientHeader.jsp" />

<main>

    <p><h2><fmt:message key="text.welcome" bundle="${ rb }"/></h2></p>

</main>

<c:import url="../static/footer.jsp" />

</body>

</html>
