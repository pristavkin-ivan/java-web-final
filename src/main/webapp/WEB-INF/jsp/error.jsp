<%--
  Created by IntelliJ IDEA.
  User: Ivan
  Date: 02.03.2021
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
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
