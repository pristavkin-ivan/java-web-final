<%--
  Created by IntelliJ IDEA.
  User: Ivan
  Date: 02.03.2021
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <title>Sign up</title>
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
            <span><b>Name</b> </span>
            <br>
            <input type="text" name="name" id="name"/>

            <br>
            <span><b>Login</b> </span>
            <br>
            <input type="text" name="login" id="login"/>

            <br>
            <span><b>Password</b> </span>
            <br>
            <input type="password" name="password" id = "password"/>

            <br>
            <button type="submit">sign un</button>

            <br>
            <a href="${pageContext.request.contextPath}/go?command=login">Login</a>
            <br>
            <p id="errorLabel">${error}</p>

        </div>
    </form>
</div>

</main>

</body>

<c:import url="static/footer.jsp" />

</html>
