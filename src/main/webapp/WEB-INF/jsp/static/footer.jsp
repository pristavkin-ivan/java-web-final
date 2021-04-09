<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customTags" prefix="ctg"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    <c:import url="${pageContext.request.contextPath}/WEB-INF/css/headerAndFooter.css"/>
</style>

<fmt:bundle basename="pagecontent" prefix="footer.">

    <footer>
        <p>
            <b><fmt:message key="info"/></b>
            <span style="text-align: right;right: 0; margin-left: 40px"><ctg:get-time/></span>
        </p>
        <ul>
            <li><b><a href="/go?command=localize&localization=en_US">EN</a></b></li>
            <li><b><a href="/go?command=localize&localization=ru_RU">RU</a></b></li>
            <li><b><a href="/go?command=localize&localization=be_BY">BY</a></b></li>
        </ul>

    </footer>
</fmt:bundle>