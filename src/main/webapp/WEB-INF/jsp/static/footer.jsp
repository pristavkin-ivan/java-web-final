<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="en_us" scope="session"/>



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
</style>

<fmt:bundle basename="pagecontent_en_us" prefix="footer.">
    <footer>
        <p><fmt:message key="info"/></p>
    </footer>
</fmt:bundle>