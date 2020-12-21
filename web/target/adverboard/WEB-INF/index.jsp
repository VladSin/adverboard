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
    <title>Main page</title>
    <link rel="stylesheet" type="text/css" href="assests/css/backgroundStyle.css"/>
</head>

<header>
    <center>
        <h1 style="color: #000385"> Welcome!!! </h1>
        <h3 style="color: #000385">On our site you can see a huge number amazing exciting and beautiful advertisements!</h3>
        <form action="${pageContext.request.contextPath}/web/locations" method="get">
            <input type="submit" value="Watch ADS"/>
        </form>
    </center>
</header>

<body>
<center>
    <h3 style="color: #000385">Login for administrator only.</h3>
    <form action="${pageContext.request.contextPath}/admin/login" method="get">
        <input type="submit" value="Log in"/>
    </form>
    <form action="${pageContext.request.contextPath}/admin/register" method="get">
        <input type="submit" value="Sign in"/>
    </form>
</center>
</body>

<footer>
    <center>
        <p style="color: #000385">Do you want to post your advertisement on our website? Download our application: Adverboard</p>
    </center>
</footer>

</html>
