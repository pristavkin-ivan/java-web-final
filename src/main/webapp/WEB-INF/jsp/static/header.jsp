<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<fmt:setBundle basename="pagecontent" var="rb"/>

<style>
    header ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #5970de;
    }

    header li {
        float: left;
    }

    header li a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    header li a:hover {
        background-color: #111111;
    }
</style>

<header style="margin-bottom: 5px">

<ul>
    <li><a href="${pageContext.request.contextPath}/go?command=default"><fmt:message key="title.home" bundle="${rb}"/></a></li>
    <c:choose>

        <c:when test="${sessionScope.login eq 'admin' and not empty sessionScope.isInstructor}">
            <li>
                <a href="${pageContext.request.contextPath}/go?command=show_all_trainings">
                    <fmt:message key="title.orders" bundle="${rb}"/>
                </a>
            </li>
        </c:when>

        <c:otherwise>

            <li>
                <a href="${pageContext.request.contextPath}/go?command=show_profile">
                    <fmt:message key="title.profile" bundle="${rb}"/>
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/go?command=show_trainings">
                    <fmt:message key="title.myTrainings" bundle="${rb}"/>
                </a>
            </li>

        </c:otherwise>

    </c:choose>
    <li>
        <a href="${pageContext.request.contextPath}/go?command=show_instructors">
        <fmt:message key="title.instructors" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/go?command=calculate_calories">
            <fmt:message key="title.calculator" bundle="${rb}"/>
        </a>
    </li>
    <li><a href="${pageContext.request.contextPath}/go?command=logout"><fmt:message key="nav.logout" bundle="${rb}"/></a></li>
    <c:if test="${empty sessionScope.isInstructor}">
    <li style="text-align: right; margin-right: 15px; margin-top: 15px; float:right; color: #e5e7e7">
        <fmt:message key="label.balance" bundle="${rb}"/>
        <fmt:formatNumber value="${sessionScope.balance}" type="currency"/>
    </li>
    </c:if>
</ul>

</header>
