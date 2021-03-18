<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="../static/localization.jsp"/>

<fmt:setBundle basename="pagecontent" var="pageContent"/>

<html>
<head>
    <title><fmt:message key="title.newTraining" bundle="${pageContent}"/></title>
</head>

<body>

<c:import url="../static/header.jsp"/>

<main>
    <form method="post" action="${pageContext.request.contextPath}/go">

        <div>
            <input type="hidden" name="command" value="create_training"/>

            <br>
            <span><b><fmt:message key="label.instructor" bundle="${pageContent}"/></b> </span>
            <br>
            <input type="text" name="instructorName" id="name" />

            <br>
            <span><b><fmt:message key="label.amount" bundle="${pageContent}"/></b> </span>
            <br>
            <input name="amount" type="number" min="1" />

            <br>
            <span><b><fmt:message key="label.difficulty" bundle="${pageContent}"/></b> </span>
            <br>
            <input name="difficulty" type="number" min="1" max="3" />

            <br>
            <span><b><fmt:message key="label.price" bundle="${pageContent}"/></b> </span>
            <br>

            <br>
            <button type="submit"><fmt:message key="label.pay" bundle="${pageContent}"/></button>

            <br>
            <p id="errorLabel">${error}</p>

        </div>
    </form>
</main>

<c:import url="../static/footer.jsp"/>

</body>

</html>
