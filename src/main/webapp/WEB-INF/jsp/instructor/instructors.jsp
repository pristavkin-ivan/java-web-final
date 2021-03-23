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
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/table.css"/>
        main {
            margin: 0 10%;
            background-color: #cee3fa;
        }
    </style>
</head>

<body>

<c:import url="../static/header.jsp" />

<h1><fmt:message key="h1.instructors" bundle="${rb}"/></h1>

<main>

    <c:if test="${not empty sessionScope.isInstructor and  sessionScope.login eq 'admin'}">

        <p style="text-align: right; margin-right: 20px">
            <a href="/go?command=signup">
                <b><fmt:message key="label.createInstructor" bundle="${rb}"/></b>
            </a>
        </p>

    </c:if>

        <c:if test="${not empty instructors}">
        <table>
            <tr>
                <th><fmt:message key="label.name" bundle="${rb}"/></th>
                <th><fmt:message key="label.photo" bundle="${rb}"/></th>
                <th><fmt:message key="label.iInfo" bundle="${rb}"/></th>
            </tr>
            <c:forEach var="instructor" items="${instructors}">
                <tr>
                    <td><b>${instructor.name}</b></td>
                    <td><img src="${instructor.url}"/></td>
                    <td>
                        <c:if test="${not empty sessionScope.isInstructor and  sessionScope.login eq 'admin'}">
                            <a style="color: red" href="/go?command=delete_profile&deleteId=${instructor.id}">Delete</a>
                        </c:if>
                        <span>${instructor.info}</span>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
</main>

<c:import url="../static/footer.jsp" />

</body>

</html>
