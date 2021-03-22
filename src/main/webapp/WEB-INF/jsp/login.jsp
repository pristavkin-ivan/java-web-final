<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="static/localization.jsp"/>

<fmt:setBundle basename="pagecontent" var="rb"/>
<html>
<head>
    <title><fmt:message key="title.authorization" bundle="${rb}"/></title>
    <style>
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/formsStyle.css"/>
    </style>
</head>
<body>

<main>
    <div id="form">
        <form method="post" action="${pageContext.request.contextPath}/go">
            <div>
                <input type="hidden" name="command" value="login"/>

                <br>
                <span><b><fmt:message key="label.login" bundle="${rb}"/></b> </span>
                <br>
                <input type="text" id="login" name="login"/>

                <br>
                <span><b><fmt:message key="label.password" bundle="${rb}"/></b> </span>
                <br>
                <input type="password" id="password" name="password"/>

                <br>
                <button type="submit"><fmt:message key="label.login" bundle="${rb}"/></button>

                <br>
                <input type="checkbox" name="isInstructor" value="true"/>
                <fmt:message key="label.iAmInstructor" bundle="${rb}"/>

                <br>
                <a href="${pageContext.request.contextPath}/go?command=signup">
                    <fmt:message key="label.signup" bundle="${rb}"/>
                </a>

                <br>
                <p id="errorLabel">${error}</p>
            </div>
        </form>
    </div>
</main>

</body>

<c:import url="static/footer.jsp" />

</html>
