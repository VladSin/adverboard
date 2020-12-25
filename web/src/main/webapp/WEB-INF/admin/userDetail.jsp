<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Monitoring Billboards</title>
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
    <h2 style="color: #000385" >Monitoring Billboards</h2>
    <table>
        <tr><th>Id</th><th>Locale</th><th>Price</th><th>GroupId</th><th>Action</th></tr>
        <c:if test="${billboards != null}">
            <c:forEach items="${billboards}" var="billboard">
                <tr>
                    <td style="color: green">${billboard.id}</td>
                    <td style="color: green">${billboard.location}</td>
                    <td style="color: green">${billboard.price}</td>
                    <td style="color: green">${billboard.groupId}</td>
                    <td style="color: green">
                        <form action="${pageContext.request.contextPath}/admin/billboard/${billboard.id}" method="get">
                            <input type="submit" value="Check ads"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</center>
</body>
</html>
