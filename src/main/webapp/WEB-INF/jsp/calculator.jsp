<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="pagecontent" var="pageContent"/>

<html>
<head>
    <title><fmt:message key="title.calculator" bundle="${pageContent}"/></title>
    <style>
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/profile.css"/>
    </style>
</head>
<body>

<c:import url="static/header.jsp"/>

<main>
    <form method="post" action="${pageContext.request.contextPath}/go">
        <div>

            <input type="hidden" name="command" value="calculate_calories"/>

            <br>
            <span><b><fmt:message key="label.height" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="number" name="height" id = "height" required="" min="0.0" step="0.1"/>

            <br>
            <span><b><fmt:message key="label.weight" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="number" name="weight" id = "weight" required="" min="0.0" step="0.1"/>

            <br>
            <input type="radio" name="gender" id="rad" value="male"/><fmt:message key="label.male" bundle="${pageContent}"/>
            <input type="radio" name="gender" id="rad" value="female"/><fmt:message key="label.female" bundle="${pageContent}"/>

            <br>
            <button type="submit"><fmt:message key="label.calculate" bundle="${pageContent}"/></button>

            <br>
            <p><fmt:message key="info.normal" bundle="${pageContent}"/>${normal}</p>

            <br>
            <p><fmt:message key="info.loss" bundle="${pageContent}"/>${loss}</p>

            <br>
            <p><fmt:message key="info.gain" bundle="${pageContent}"/>${gain}</p>

        </div>

    </form>
</main>

<c:import url="static/footer.jsp"/>

</body>
</html>
