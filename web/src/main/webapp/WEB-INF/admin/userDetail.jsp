<%--
  Created by IntelliJ IDEA.
  User: Vlads
  Date: 24.12.2020
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Monitoring Billboards</title>
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

<footer>
    <center>
        <p style="color: #000385">Do you want to post your advertisement on our website? Download our application: Adverboard</p>
    </center>
</footer>

</html>
