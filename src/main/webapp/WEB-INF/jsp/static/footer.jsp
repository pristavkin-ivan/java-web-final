<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customTags" prefix="ctg"%>

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
        color: #0a0000;
    }

    footer li {
        float:right;
        margin: 10px;
        color:black;
    }

</style>

<fmt:bundle basename="pagecontent" prefix="footer.">

    <footer>
        <p>
            <b><fmt:message key="info"/></b>
            <span style="text-align: right;right: 0; margin-left: 40px"><ctg:get-time/></span>
        </p>
        <ul>
            <b>
            <li><a href="/go?localization=en">EN</a></li>
            <li><a href="/go?localization=ru">RU</a></li>
            <li><a href="/go?localization=be">BE</a></li>
            </b>
        </ul>

    </footer>
</fmt:bundle>