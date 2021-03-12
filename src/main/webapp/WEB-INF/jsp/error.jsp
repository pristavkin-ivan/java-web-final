<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Error</title>
</head>

<body>

<a href="${pageContext.request.contextPath}/go?command=default">Home...</a>

<main>
    hey!
</main>

</body>

<c:import url="static/footer.jsp" />

</html>
