<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/jsp/common/jstl-connect.jsp" %>
<html lang="en">
<head>
    <title>Charity Chess Games</title>
    <%@include file="WEB-INF/jsp/common/css-connect.jsp" %>
    <%@include file="WEB-INF/jsp/common/js-connect.jsp" %>
</head>
<body>
<%@ include file="WEB-INF/jsp/common/header.jsp" %>
<div style="text-align: center; font-size: 50px">
    Sign in Charity Chess Games
</div>
<div class="container mt-5 mb-5">
    <div class="row d-flex align-items-center justify-content-center">
        <div class="col-md-7">
            <div class="card px-5 py-5"><span class="circle"><i class="fa fa-check"></i></span>
                <form action="" method="post">
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                            <label for="floatingInput">Email address</label>
                            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
                            <label for="floatingPassword">Password</label>
                        </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
                <div class="text-center mt-4"><span>You aren't a member?</span> <a
                        href="<c:url value="/registration"/>"
                        class="text-decoration-none">Registration</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
