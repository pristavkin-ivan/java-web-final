<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="../static/localization.jsp"/>

<fmt:bundle basename="pagecontent">

<html>
<head>
    <title><fmt:message key="title.trainingDetails"/></title>
</head>
<body>

<c:import url="../static/clientHeader.jsp" />

<main>
    <ul>
        <li><fmt:message key="label.orderNumber"/>${training.id}.</li>
        <li><fmt:message key="label.instructor"/> ${training.instructorName}.</li>
        <li><fmt:message key="label.amount"/>${training.amount}.</li>
        <li><fmt:message key="label.price"/>${training.price}.</li>

    </ul>
    <br>

    <form>
        <span><fmt:message key="label.comment"/></span>

        <br>
        <textarea name="comment" id="comment">${training.comment}</textarea>

        <br>
        <button type="submit" ><fmt:message key="label.change"/></button>
    </form>

</main>

<c:import url="../static/footer.jsp" />

</body>
</html>

</fmt:bundle>