<%--
  Created by IntelliJ IDEA.
  User: Vlads
  Date: 27.11.2020
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Authorization</title>
</head>

<header>
    <form action="${pageContext.request.contextPath}/login" method="get">
        <input type="submit" value="For admin"/>
    </form>
    <form action="${pageContext.request.contextPath}/" method="get">
        <input type="submit" value="For admin"/>
    </form>
</header>

<body>
<center>
    <h1 style="color: green"> Welcome!!! </h1>
    <form action="${pageContext.request.contextPath}/web" method="get">
        <input type="submit" value="Watch ADS"/>
    </form>
    <p style="color: green">Do you want to post your advertisement on our website? Then download our application: Adverboard</p>
</center>
</body>

<footer>
    <h1 style="color: green">14.12.2020 time 20.50</h1>
</footer>

</html>
