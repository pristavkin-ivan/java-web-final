<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:choose>

    <c:when test="${not empty cookie.locale.value}">
        <fmt:setLocale value="${cookie.locale.value}" scope="application"/>
    </c:when>

    <c:otherwise>
        <fmt:setLocale value="en_US" scope="application"/>
    </c:otherwise>

</c:choose>
