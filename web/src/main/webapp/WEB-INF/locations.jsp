<%--
  Created by IntelliJ IDEA.
  User: Vlads
  Date: 15.12.2020
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Locations</title>
</head>
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
</html>

