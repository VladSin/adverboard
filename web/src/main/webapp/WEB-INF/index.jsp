<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Main page</title>
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
    <link href="<c:url value="/resources/css/backgroundStyle.css" />" rel="stylesheet">
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
        <input class="form-input" type="submit" value="Log in"/>
    </form>
</center>
</body>

<footer>
    <center>
        <h6 style="color: #000385">Do you want to post your advertisement on our website? Download our application: Adverboard</h6>
    </center>
</footer>
</html>
