<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Locations</title>
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
    <link href="<c:url value="/resources/css/backgroundStyle.css" />" rel="stylesheet">
</head>

<header>
    <form action="${pageContext.request.contextPath}/" method="get">
        <input type="submit" value="Let's GO back!"/>
    </form>
</header>

<body>
<center>
    <h1 style="color: darkslateblue">Locations</h1>

    <form action="${pageContext.request.contextPath}/web/stream" method="post">
        <select name="location">
            <optgroup label="location">
                <c:if test="${locations != null}">
                    <c:forEach items="${locations}" var="location">
                        <option value="${location.location}">${location.location}</option>
                    </c:forEach>
                </c:if>
            </optgroup>
        </select>
        <input type="submit" name="location" value="Choose" />
    </form>

</center>
</body>

<footer>
    <center>
        <p style="color: #000385">Do you want to post your advertisement on our website? Download our application: Adverboard</p>
    </center>
</footer>
</html>

