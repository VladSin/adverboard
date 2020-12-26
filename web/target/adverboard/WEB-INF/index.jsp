<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Main page</title>
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
    <link href="<c:url value="/resources/css/backgroundStyle.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/showSlides.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/headerStyle.css" />" rel="stylesheet">
</head>

<header>
    <center>
        <form action="${pageContext.request.contextPath}/web/locations" method="get">
            <h1 style="color: #000385"> Welcome!!! </h1>
            <h3 style="color: #000385">On our site you can see a huge number amazing exciting and beautiful advertisements!</h3>
            <input type="submit" value="Watch ADS"/>
        </form>
<%--        <h3 style="color: #000385">Login for administrator only.</h3>--%>
        <form action="${pageContext.request.contextPath}/adminAuth/login" method="get">
            <input class="form-input" type="submit" value="Log in"/>
        </form>
    </center>
</header>

<body>
<!-- Slideshow container -->
<div class="slideshow-container">
    <c:if test="${ads != null}">
        <c:forEach items="${text}" var="t">
            <c:forEach items="${ads}" var="ad">
                <!-- Full-width images with number and caption text -->
                <div class="mySlides fade">
                    <img src="${ad.link}" style="width:100%">
                    <div class="text">Only the best content!</div>
                </div>
            </c:forEach>
        </c:forEach>
    </c:if>
</div>
<br>
<script src="<c:url value="/resources/js/showSlidesAuto.js" />"></script>
</body>

<footer>
    <center>
        <h3 style="color: #000385">Do you want to post your advertisement on our website? Download our application: Adverboard</h3>
    </center>
</footer>
</html>
