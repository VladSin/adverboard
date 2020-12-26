<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Monitoring Users</title>
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
    <link href="<c:url value="/resources/css/backgroundStyle.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/tableStyle.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/aStyle.css" />" rel="stylesheet">
</head>

<header>
    <a href="${pageContext.request.contextPath}/adminAuth/register">Sign In</a>
    <a href="${pageContext.request.contextPath}/logout/admin">Logout</a>
    <a href="${pageContext.request.contextPath}/">Go Back</a>
</header>

<body>
<center>
    <h2 style="color: #000385" >Monitoring Users</h2>
    <table>
        <tr><th>Id</th><th>Username</th><th>Email</th><th>Action</th></tr>
        <c:if test="${users != null}">
            <c:forEach items="${users}" var="user">
                <tr>
                    <td style="color: green">${user.id}</td>
                    <td style="color: green">${user.username}</td>
                    <td style="color: green">${user.email}</td>
                    <td style="color: green">
                        <form action="${pageContext.request.contextPath}/admin/user/${user.id}" method="get">
                            <input type="submit" value="More Detail"/>
                        </form>
                        <form action="${pageContext.request.contextPath}/admin/delete/user/${user.id}" method="get">
                            <input type="submit" value="Delete"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</center>
</body>
</html>
