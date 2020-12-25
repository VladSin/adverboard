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
</head>

<header>
    <form action="${pageContext.request.contextPath}/" method="get">
        <input type="submit" value="Let's GO back!"/>
    </form>
    <form action="${pageContext.request.contextPath}/admin/register" method="get">
        <input type="submit" value="Sign in"/>
    </form>
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

<footer>
    <link rel="stylesheet" type="text/css" href="assests/css/footerStyle.css"/>
    <center>
        <p style="color: #000385">Do you want to post your advertisement on our website? Download our application: Adverboard</p>
    </center>
</footer>
</html>
