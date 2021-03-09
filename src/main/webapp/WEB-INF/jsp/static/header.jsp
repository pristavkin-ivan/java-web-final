<style>
    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #5970de;
    }

    li {
        float: left;
    }

    li a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    li a:hover {
        background-color: #111111;
    }
</style>

<header style="margin-bottom: 10px">

<ul>
    <li><a href="${pageContext.request.contextPath}/go?command=default">Home</a></li>
    <li><a href="">Instructors</a></li>
    <li><a href="">Equipment</a></li>
    <li><a href="${pageContext.request.contextPath}/go?command=logout">Log out</a></li>
</ul>

</header>