<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="../static/localization.jsp"/>

<fmt:bundle basename="pagecontent">

<html>
<head>
    <style>
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/profile.css"/>
    </style>
    <title><fmt:message key="title.trainingDetails"/></title>
</head>
<body>

<c:import url="../static/header.jsp" />

<main >
    <div>
    <ul>
        <li><fmt:message key="label.orderNumber"/>${training.id}.</li>
        <c:if test="${empty sessionScope.isInstructor or (not empty sessionScope.isInstructor and sessionScope.login == 'admin')}">
            <li><fmt:message key="label.instructor"/> ${training.instructorName}.</li>
        </c:if>
        <c:if test="${not empty sessionScope.isInstructor}">
            <li><fmt:message key="label.client"/> ${training.clientName}.</li>
        </c:if>
        <li><fmt:message key="label.amount"/>${training.amount}.</li>
        <li><fmt:message key="label.price"/>${training.price}.</li>

    </ul>
    <br>

    <span><fmt:message key="label.comment"/></span>
    <br>

    <c:choose>

        <c:when test="${empty sessionScope.isInstructor}">
            <form>

                <textarea name="comment" id="comment">${training.comment}</textarea>

                <br>
                <button type="submit" ><fmt:message key="label.change"/></button>

            </form>
        </c:when>

        <c:otherwise>

            <p>${training.comment}</p>

        </c:otherwise>
    </c:choose>
        <br>
        <h2 style="text-align: center"><a href="/go?command=delete_training&deleteId=${training.id}" style="color: red;">Delete training</a></h2>
    </div>

</main>

<c:import url="../static/footer.jsp" />

</body>
</html>

</fmt:bundle>