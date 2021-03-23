<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="../static/localization.jsp"/>

<fmt:bundle basename="pagecontent">

<html>
<head>
    <style>
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/olStyle.css"/>
    </style>
    <title>
<c:choose>

    <c:when test="${sessionScope.login eq 'admin' and not empty sessionScope.isInstructor}">
        <fmt:message key="title.orders" />
    </c:when>

    <c:otherwise>
        <fmt:message key="title.myTrainings" />
    </c:otherwise>

</c:choose>
    </title>
</head>
<body>

<c:import url="../static/header.jsp"/>

<main>
    <c:if test="${empty sessionScope.isInstructor }">

        <p style="text-align: right; margin-right: 20px">
            <a href="/go?command=create_training">
                <b><fmt:message key="label.createTraining"/></b>
            </a>
        </p>

    </c:if>

    <c:if test="${not empty trainings}">

        <ol class="bullet">
            <c:forEach var="training" items="${trainings}">
                <li>
                    <p>
                        <fmt:message key="label.orderNumber"/>${training.id}.
                        <c:if test="${empty sessionScope.isInstructor || sessionScope.login eq 'admin' }">
                            <fmt:message key="label.instructor"/> ${training.instructorName}.
                        </c:if>
                        <c:if test="${ not empty sessionScope.isInstructor }">
                            <fmt:message key="label.client"/> ${training.clientName}.
                        </c:if>
                            <fmt:message key="label.amount"/>${training.amount}.
                            <c:if test="${sessionScope.login eq 'admin' and not empty sessionScope.isInstructor}">
                                <fmt:message key="label.price"/>${training.price}.
                            </c:if>
                        <a href="/go?command=inspect_training&trainingId=${training.id}"><fmt:message key="label.details"/></a>
                    </p>
                </li>
                <br>
            </c:forEach>
        </ol>
    </c:if>

</main>

<c:import url="../static/footer.jsp"/>

</body>

</html>
</fmt:bundle>