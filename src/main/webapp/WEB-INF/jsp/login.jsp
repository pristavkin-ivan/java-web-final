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
    <title>Authorization</title>
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
                <span><b>Login</b> </span>
                <br>
                <input type="text" id="login" name="login"/>

                <br>
                <span><b>Password</b> </span>
                <br>
                <input type="password" id="password" name="password"/>

                <br>
                <button type="submit">login</button>

                <br>
                <input type="checkbox" name="isInstructor" value="true">I am instructor

                <br>
                <a href="${pageContext.request.contextPath}/go?command=signup">Sign up</a>

                <br>
                <p id="errorLabel">${error}</p>
            </div>
        </form>
    </div>
</main>

</body>

<c:import url="static/footer.jsp" />

</html>
