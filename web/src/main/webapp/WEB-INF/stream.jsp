<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Stream</title>
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
    <link href="<c:url value="/resources/css/showSlides.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/backgroundStyle.css" />" rel="stylesheet">
</head>

<header>
    <form action="${pageContext.request.contextPath}/" method="get">
        <input type="submit" value="Let's GO back!"/>
    </form>
</header>

<body>
<!-- Slideshow container -->
<div class="slideshow-container">
<c:if test="${ads != null}">
    <c:forEach items="${ads}" var="ad">
    <!-- Full-width images with number and caption text -->
    <div class="mySlides fade">
        <img src="${ad.link}" style="width:100%">
        <div class="text">Caption Text</div>
    </div>
    </c:forEach>
</c:if>
</div>
<br>

<!-- The dots/circles -->
<div style="text-align:center">
    <span class="dot" onclick="currentSlide(1)"></span>
    <span class="dot" onclick="currentSlide(2)"></span>
    <span class="dot" onclick="currentSlide(3)"></span>
</div>

<script src="<c:url value="/resources/js/showSlides.js" />"></script>

</body>

</html>
