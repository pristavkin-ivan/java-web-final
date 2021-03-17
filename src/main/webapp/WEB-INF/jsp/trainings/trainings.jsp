<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="../static/localization.jsp"/>

<fmt:bundle basename="pagecontent">

<html>
<head>
    <title><fmt:message key="title.trainings"/></title>
</head>

<body>

<c:import url="../static/clientHeader.jsp"/>

<main>
    <c:if test="${empty sessionScope.isInstructor}">

        <p style="text-align: right; margin-right: 20px">
            <a href="/go?command=create_training">
                <b><fmt:message key="label.createTraining"/></b>
            </a>
        </p>

    </c:if>

    <c:if test="${not empty trainings}">
        <ul type="square">
            <c:forEach var="training" items="${trainings}">
                <li>
                    <p>
                        <fmt:message key="label.orderNumber"/>${training.id}.
                        <c:if test="${empty sessionScope.isInstructor}">
                            <fmt:message key="label.instructor"/> ${training.instructorName}.
                        </c:if>
                        <c:if test="${ sessionScope.isInstructor == true }">
                            <fmt:message key="label.client"/> ${training.clientName}.
                        </c:if>
                        <fmt:message key="label.amount"/>${training.amount}.
                        <a href="/go?command=inspect_training&trainingId=${training.id}"><fmt:message key="label.details"/></a>
                    </p>
                </li>
                <br>
            </c:forEach>
        </ul>
    </c:if>

</main>

<c:import url="../static/footer.jsp"/>

</body>

</html>
</fmt:bundle>