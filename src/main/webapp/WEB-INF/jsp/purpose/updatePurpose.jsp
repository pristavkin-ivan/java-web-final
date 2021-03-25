<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="../static/localization.jsp"/>

<fmt:setBundle basename="pagecontent" var="pageContent"/>

<html>
<head>
    <title><fmt:message key="title.purpose" bundle="${pageContent}"/></title>
    <style>
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/profile.css"/>
    </style>
</head>

<body>

<c:import url="../static/header.jsp"/>

<main>

    <form method="post" action="${pageContext.request.contextPath}/go">

        <div>
            <input type="hidden" name="command" value="update_purpose"/>
            <input type="hidden" name="purposeId" value="${param.purposeId}"/>
            <input type="hidden" name="trainingId" value="${param.trainingId}"/>

            <br>
            <span><b><fmt:message key="label.exercise" bundle="${pageContent}"/></b> </span>
            <br>
            <select name="exercise">

                <option value=""></option>
                <c:forEach var="ex" items="${exercises}">
                    <option value="${ex.name}">${ex}</option>
                </c:forEach>
            </select>

            <br>
            <span><b><fmt:message key="label.equipment" bundle="${pageContent}"/></b> </span>
            <br>
            <select name="equipment">
                <option value=""></option>
                <c:forEach var="equip" items="${equipments}">
                    <option value="${equip.name}">${equip}</option>
                </c:forEach>
            </select>

            <br>
            <span><b><fmt:message key="label.food" bundle="${pageContent}"/></b> </span>
            <br>
            <select name="food">
                <option value=""></option>
                <c:forEach var="f" items="${foods}">
                    <option value="${f.name}">${f}</option>
                </c:forEach>
            </select>

            <br>
            <button type="submit"><fmt:message key="label.update" bundle="${pageContent}"/></button>

            <br>
            <p id="errorLabel">${error}</p>
        </div>
    </form>

</main>

<c:import url="../static/footer.jsp"/>

</body>
</html>
