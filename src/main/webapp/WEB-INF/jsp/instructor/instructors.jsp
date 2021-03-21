<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="../static/localization.jsp"/>

<fmt:setBundle basename="pagecontent" var="rb"/>

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

<c:import url="../static/header.jsp" />

<h1 style="text-align: center; color: blue"><fmt:message key="h1.instructors" bundle="${rb}"/></h1>

<main>

    <c:if test="${not empty sessionScope.isInstructor and  sessionScope.login eq 'admin'}">

        <p style="text-align: right; margin-right: 20px">
            <a href="/go?command=signup">
                <b><fmt:message key="label.createInstructor" bundle="${rb}"/></b>
            </a>
        </p>

    </c:if>

        <c:if test="${not empty instructors}">
        <ul type="square">
            <c:forEach var="instructor" items="${instructors}">
            <li>
                <span ><b>${instructor.name}</b></span><br><br>
                    <img style="text-align: center" src="${instructor.url}"/>
                <c:if test="${not empty sessionScope.isInstructor and  sessionScope.login eq 'admin'}">
                    <a href="/go?command=delete_profile&deleteId=${instructor.id}">Delete</a>
                </c:if>
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
