<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Authorization</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/backgroundStyle.css" />" rel="stylesheet">
</head>

<header>
    <center>
        <h1 style="color: #000385"> Welcome!!! </h1>
        <h3 style="color: #000385">On our site you can see a huge number amazing exciting and beautiful advertisements!</h3>
        <form action="${pageContext.request.contextPath}/web" method="get">
            <input type="submit" value="Watch ADS"/>
        </form>
    </center>
</header>

<body>
<center>
    <h3 style="color: #000385">Login for administrator only.</h3>
    <form action="${pageContext.request.contextPath}/admin/login" method="post">
        <label for="login"></label>
        <input id="login" type="text" name="login" placeholder="User name"/><br/>

        <label for="password"></label>
        <input id="password" type="password" name="password" placeholder="Password"/><br/>

        <input type="submit" value="Sign in"/>
    </form>
</center>
</body>

<footer>
    <link rel="stylesheet" type="text/css" href="assests/css/footerStyle.css"/>
    <center>
        <p style="color: #000385">Do you want to post your advertisement on our website? Then download our application: Adverboard</p>
    </center>
</footer>
</html>
