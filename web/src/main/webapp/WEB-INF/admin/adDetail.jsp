<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Monitoring Ads</title>
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
    <link href="<c:url value="/resources/css/backgroundStyle.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/tableStyle.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/aStyle.css" />" rel="stylesheet">
</head>

<header>
    <a href="${pageContext.request.contextPath}/adminAuth/register">Sign In</a>
    <a href="${pageContext.request.contextPath}/logout/admin">Logout</a>
</header>

<body>
<center>
    <h2 style="color: #000385" >Monitoring Ads</h2>
    <table>
        <tr><th>Billboard with id: ${id}</th><th>Action</th></tr>
        <c:if test="${ads != null}">
            <c:forEach items="${ads}" var="ad">
                <tr>
                    <td style="color: green">
                        <iframe width="400" height="300" src="${ad.link}" frameborder="0" scrolling="no" align="absmiddle"></iframe>
                    </td>
                    <td style="color: green">
                        <form action="${pageContext.request.contextPath}/admin/verify/ad/${ad.id}" method="get">
                            <input type="submit" value="Verify"/>
                        </form>
                        <form action="${pageContext.request.contextPath}/admin/delete/ad/${ad.id}" method="delete">
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
