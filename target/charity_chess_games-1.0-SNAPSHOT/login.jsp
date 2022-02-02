<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/jstl-connect.jsp" %>
<html lang="en">
<head>
    <title>Charity Chess Games</title>
    <%@include file="/WEB-INF/jsp/common/css-connect.jsp" %>
    <%@include file="/WEB-INF/jsp/common/js-connect.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
<div style="text-align: center; font-size: 50px">
    Sign in Charity Chess Games
</div>
<form>
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Email address</label>
        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
        <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
    </div>
    <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>
