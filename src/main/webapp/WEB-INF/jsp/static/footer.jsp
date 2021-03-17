<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>
    footer {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        background-color: #7db4e3;
        color: #0a0000;
        text-align: center;
        opacity: 0.7;
    }

    footer ul {
        position: fixed;
        bottom: 0;
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: transparent;
    }

    footer li {
        float:right;
        margin: 10px;
    }

</style>

<fmt:bundle basename="pagecontent" prefix="footer.">

    <footer>
        <p><fmt:message key="info"/></p>
        <ul>
            <li><a href="/go?localization=en">EN</a></li>
            <li><a href="/go?localization=ru">RU</a></li>
            <li><a href="/go?localization=be">BE</a></li>
        </ul>
    </footer>
</fmt:bundle>