<%--
  Created by IntelliJ IDEA.
  User: Vlads
  Date: 15.12.2020
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Adverboards account</title>
    <link rel="stylesheet" type="text/css" href="assests/css/backgroundStyle.css"/>
</head>

<header>
    <center>
        <h1 style="color: #000385"> Nice to see you! </h1>
        <h3 style="color: #000385">On our site you can see a huge number amazing exciting and beautiful advertisements!</h3>
        <form action="${pageContext.request.contextPath}/web" method="get">
            <input type="submit" value="Watch ADS"/>
        </form>
    </center>
</header>

<body>
<center>
    <h2 style="color: #000385" >System management.</h2>
    <form action="${pageContext.request.contextPath}/web" method="get">
        <input type="submit" value="Billboard list"/>
    </form>
    <form action="${pageContext.request.contextPath}/web" method="get">
        <input type="submit" value="User list"/>
    </form>
</center>
</body>

<footer>
    <center>
        <p style="color: #000385">Do you want to post your advertisement on our website? Download our application: Adverboard</p>
    </center>
</footer>

</html>
