<%--
  Created by IntelliJ IDEA.
  User: Vlads
  Date: 24.12.2020
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Monitoring Ads</title>
    <link rel="stylesheet" type="text/css" href="assests/css/backgroundStyle.css"/>
    <link rel="stylesheet" type="text/css" href="assests/css/tableStyle.css"/>
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
    <h2 style="color: #000385" >Monitoring Ads</h2>
    <table>
        <tr><th>Billboard with id: ${id}</th><th>Action</th></tr>
        <c:if test="${ads != null}">
            <c:forEach items="${ads}" var="ad">
                <tr>
                    <td style="color: green">
                        <iframe width="400" height="300" src="${ad.link}" frameborder="120" allowfullscreen></iframe>
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

<footer>
    <link rel="stylesheet" type="text/css" href="assests/css/footerStyle.css"/>
    <center>
        <p style="color: #000385">Do you want to post your advertisement on our website? Download our application: Adverboard</p>
    </center>
</footer>

</html>
