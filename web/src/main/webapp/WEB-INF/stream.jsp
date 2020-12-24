<%--
  Created by IntelliJ IDEA.
  User: Vlads
  Date: 15.12.2020
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Stream</title>
    <link rel="stylesheet" type="text/css" href="assests/css/showSlides.css"/>
    <script src="assests/js/showSlides.js"></script>
</head>

<body>
<!-- Slideshow container -->
<div class="slideshow-container">
<c:if test="${ads != null}">
    <c:forEach items="${ads}" var="ad">
    <!-- Full-width images with number and caption text -->
    <div class="mySlides fade">
        <div class="numbertext">${ad.id}</div>
        <img src="${ad.link}" style="width:100%">
        <div class="text">Caption Text</div>
    </div>
    </c:forEach>
</c:if>

    <!-- Next and previous buttons -->
    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
<br>

<!-- The dots/circles -->
<div style="text-align:center">
    <span class="dot" onclick="currentSlide(1)"></span>
    <span class="dot" onclick="currentSlide(2)"></span>
    <span class="dot" onclick="currentSlide(3)"></span>
</div>
</body>

</html>
