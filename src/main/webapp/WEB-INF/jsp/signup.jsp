<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="static/localization.jsp"/>

<fmt:setBundle basename="pagecontent" var="pageContent"/>

<html>

<head>
    <c:choose>

        <c:when test="${sessionScope.login eq 'admin' and not empty sessionScope.isInstructor}">
            <title><fmt:message key="title.newInstructor" bundle="${pageContent}"/></title>
        </c:when>

        <c:otherwise>
            <title><fmt:message key="label.signup" bundle="${pageContent}"/></title>
        </c:otherwise>

    </c:choose>
    <style>
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/formsStyle.css"/>
    </style>
</head>

<body>

<c:if test="${sessionScope.login eq 'admin' and not empty sessionScope.isInstructor}">
    <c:import url="static/header.jsp" />
</c:if>

<main>
<div id="form">

    <form method="post" action="${pageContext.request.contextPath}/go"  onsubmit="return validate()">

        <div class="form-div">
            <input type="hidden" name="command" value="signup"/>

            <br>
            <span><b><fmt:message key="label.name" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="text" name="name" id="name" class="field"/>

            <br>
            <span><b><fmt:message key="label.login" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="text" name="login" id="login" class="field"/>

            <br>
            <span><b><fmt:message key="label.password" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="password" name="password" id = "password" class="field"/>
            <c:choose>

                <c:when test="${sessionScope.login eq 'admin' and not empty sessionScope.isInstructor}">
                    <br>
                    <button type="submit"><fmt:message key="button.add" bundle="${pageContent}"/></button>
                </c:when>

                <c:otherwise>
                    <br>
                    <button type="submit" class="submitButton"><fmt:message key="label.signup" bundle="${pageContent}"/></button>

                    <br>
                    <a href="${pageContext.request.contextPath}/go?command=login">
                        <fmt:message key="label.login" bundle="${pageContent}"/>
                    </a>
                </c:otherwise>

            </c:choose>
            <br>
            <p id="errorLabel">${error}</p>

        </div>
    </form>
</div>

</main>

<script src=<c:url value="/js/formValidation.js"/>></script>
</body>

<c:import url="static/footer.jsp" />

</html>
