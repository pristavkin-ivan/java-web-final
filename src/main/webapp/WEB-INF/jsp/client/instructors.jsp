<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="en_US" scope="session"/>
<fmt:setBundle basename="pagecontent_en_us" var="rb"/>

<html>

<head>
    <title>
        <fmt:message key="title.instructors" bundle="${rb}"/>
    </title>
    <style>
        main {
            margin: 0 10%;
            background-color: #c4edfa;
        }
    </style>
</head>

<body>

<c:import url="../static/clientHeader.jsp" />

<h1 style="text-align: center; color: blue"><fmt:message key="h1.instructors" bundle="${rb}"/></h1>

<main>
        <c:if test="${not empty instructors}">
        <ul type="square">
            <c:forEach var="instructor" items="${instructors}">
            <li>
                <span ><b>${instructor.name}</b></span><br><br>
                    <img style="text-align: center" src="${instructor.url}"/>
                <span>${instructor.info}</span>
                    <br>
            </li>
                <br>
            </c:forEach>
        </ul>
        </c:if>
</main>

<c:import url="../static/footer.jsp" />

</body>

</html>
