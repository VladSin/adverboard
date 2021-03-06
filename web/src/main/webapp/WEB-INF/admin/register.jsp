<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Registration</title>
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>

<body>
<div class="overlay">
    <form action="${pageContext.request.contextPath}/adminAuth/register" method="post">
        <center>
        <div class="con">

            <header class="head-form">
                <h2>Log Up</h2>
                <p>login here using your username and password</p>
            </header>

            <br>
            <div class="field-set">
                <span class="input-item"><i class="fa fa-user-circle"></i></span>
                <input class="form-input" id="login" name="login" type="text" placeholder="@AdminName" required>
                <br>

                <span class="input-item"><i class="fa fa-user-circle"></i></span>
                <input class="form-input" id="email" name="email" type="text"  placeholder="Email"/><br/>
                <br>

                <span class="input-item"><i class="fa fa-key"></i></span>
                <input class="form-input" id="password" name="password" type="password" placeholder="Password" required>

                <button class="log-in"> Log Up </button>
                <p style="color: red">${error}</p>
            </div>
        </div>
        </center>
    </form>
</div>
<script src="<c:url value="/resources/js/index.js" />"></script>
</body>

</html>
