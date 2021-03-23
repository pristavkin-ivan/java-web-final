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

    <form method="post" action="${pageContext.request.contextPath}/go">

        <div >
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
            <span><b><fmt:message key="label.height" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="number" name="height" id = "height" required="" min="0.0" step="0.1" value="${height}"/>

            <br>
            <span><b><fmt:message key="label.weight" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="number" name="weight" id = "weight" required="" min="0.0" step="0.1" value="${weight}"/>

            <br>
            <button type="submit"><fmt:message key="label.change" bundle="${pageContent}"/></button>

            <br>
            <h2 style="text-align: center"><a href="/go?command=delete_profile" style="color: red;">Delete profile</a></h2>

            <br>
            <p id="errorLabel">${error}</p>
        </div>
    </form>

</main>

<c:import url="../static/footer.jsp"/>

</body>
</html>
