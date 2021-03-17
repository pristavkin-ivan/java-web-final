<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:choose>

    <c:when test="${sessionScope.localization eq 'en'}">
        <fmt:setLocale value="en_US" scope="session"/>
    </c:when>

    <c:when test="${sessionScope.localization eq 'ru'}">
        <fmt:setLocale value="ru_RU" scope="session"/>
    </c:when>

    <c:when test="${sessionScope.localization eq 'be'}">
        <fmt:setLocale value="be_BY" scope="session"/>
    </c:when>

</c:choose>