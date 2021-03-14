<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="en_US" scope="session"/>
<fmt:setBundle basename="pagecontent_en_us" var="rb"/>

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
    <li>
        <a href="${pageContext.request.contextPath}/go?command=show_profile">
            <fmt:message key="title.profile" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/go?command=show_trainings">
            <fmt:message key="title.trainings" bundle="${rb}"/>
        </a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/go?command=show_instructors">
        <fmt:message key="title.instructors" bundle="${rb}"/>
        </a>
    </li>
    <li><a href="${pageContext.request.contextPath}/go?command=logout"><fmt:message key="nav.logout" bundle="${rb}"/></a></li>
</ul>

</header>
