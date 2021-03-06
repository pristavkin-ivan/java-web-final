<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="../static/localization.jsp"/>

<fmt:setBundle basename="pagecontent" var="pageContent"/>

<html>
<head>
    <title><fmt:message key="title.profile" bundle="${pageContent}"/></title>
    <style>
        <c:import url="${pageContext.request.contextPath}/WEB-INF/css/profile.css"/>
    </style>
</head>

<body>

<c:import url="../static/header.jsp"/>

<main>

    <h1><fmt:message key="title.profile" bundle="${pageContent}"/></h1>

    <form method="post" action="${pageContext.request.contextPath}/go" onsubmit="return validateName()" >

        <div style="padding: 100px" class="form-div">
            <input type="hidden" name="command" value="update_profile"/>

            <br>
            <span><b><fmt:message key="label.name" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="text" name="name" id="name" value="${name}"/>

            <br>
            <span><b><fmt:message key="label.login" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="text" name="login" id="login" value="${login}"/>

            <br>
            <span><b><fmt:message key="label.info" bundle="${pageContent}"/></b> </span>
            <br>
            <textarea name="info" id="info">${info}</textarea>

            <br>
            <span><b><fmt:message key="label.photoUrl" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="text" name="photoUrl" id="photoUrl" value="${photoUrl}"/>

            <br>
            <button type="submit" ><fmt:message key="label.change" bundle="${pageContent}"/></button>

            <br>
            <p id="errorLabel">${error}</p>

        </div>
    </form>

</main>

<c:import url="../static/footer.jsp"/>
<c:import url="../static/footer.jsp"/>
</body>
</html>