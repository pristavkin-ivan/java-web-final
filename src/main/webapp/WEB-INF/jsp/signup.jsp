<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="en_US" scope="session"/>
<fmt:setBundle basename="pagecontent_en_us" var="pageContent"/>

<html>

<head>
    <title><fmt:message key="label.signup" bundle="${pageContent}"/></title>
    <style>
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/formsStyle.css"/>
    </style>
</head>

<body>

<main>
<div id="form">
    <form method="post" action="${pageContext.request.contextPath}/go">

        <div>
            <input type="hidden" name="command" value="signup"/>

            <br>
            <span><b><fmt:message key="label.name" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="text" name="name" id="name"/>

            <br>
            <span><b><fmt:message key="label.login" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="text" name="login" id="login"/>

            <br>
            <span><b><fmt:message key="label.password" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="password" name="password" id = "password"/>

            <br>
            <button type="submit"><fmt:message key="label.signup" bundle="${pageContent}"/></button>

            <br>
            <a href="${pageContext.request.contextPath}/go?command=login">
                <fmt:message key="label.login" bundle="${pageContent}"/>
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
