<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="../static/localization.jsp"/>

<fmt:bundle basename="pagecontent">

<html>
<head>
    <style>
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/profile.css"/>
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/olStyle.css"/>
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/table.css"/>
    </style>
    <title><fmt:message key="title.trainingDetails"/></title>
</head>
<body>

<c:import url="../static/header.jsp" />

<main >
    <div>
    <ul class="train">
        <li><fmt:message key="label.orderNumber"/>${training.id}.</li>
        <c:if test="${empty sessionScope.isInstructor or (not empty sessionScope.isInstructor and sessionScope.login == 'admin')}">
            <li><fmt:message key="label.instructor"/> ${training.instructorName}.</li>
        </c:if>
        <c:if test="${not empty sessionScope.isInstructor}">
            <li><fmt:message key="label.client"/> ${training.clientName}.</li>
        </c:if>
        <li><fmt:message key="label.amount"/>${training.amount}.</li>
        <li><fmt:message key="label.price"/>${training.price}.</li>

        <table style="text-align: center; width: 100%">
            <tr>
                <th><fmt:message key="label.exercise"/></th>
                <th><fmt:message key="label.equipment"/></th>
                <th><fmt:message key="label.food"/></th>
            </tr>
            <c:forEach var="purpose" items="${training.purposes}">
                <tr>
                    <td>${purpose.exercise.name}</td>
                    <td>${purpose.equipment.name}</td>
                    <td>${purpose.food.name}
                        <a style="color: red" href="/go?command=delete_purpose&purposeId=${purpose.id}&trainingId=${training.id}">
                            <fmt:message key="label.delete"/>
                        </a>
                        <a style="color: #2d62bf" href="/go?command=update_purpose&purposeId=${purpose.id}&trainingId=${training.id}">
                            <fmt:message key="label.update"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a style="color: green" href="/go?command=create_purpose&trainingId=${training.id}"><fmt:message key="button.add"/></a>


    </ul>
    <br>

    <span><fmt:message key="label.comment"/></span>
    <br>

    <c:choose>

        <c:when test="${empty sessionScope.isInstructor}">
            <form>

                <textarea name="comment" id="comment">${training.comment}</textarea>
                <input type="hidden" name="trainingId" value="${training.id}"/>
                <input type="hidden" name="command" value="comment"/>

                <br>
                <button type="submit"><fmt:message key="label.change"/></button>

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