<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../static/common/jstl-connect.jsp" %>
<html lang="en">
<head>
    <title>Charity Chess Games</title>
    <%@include file="../static/common/css-connect.jsp" %>
    <%@include file="../static/common/js-connect.jsp" %>
</head>
<body>
<%@ include file="../static/common/header.jsp" %>
<div style="text-align: center; font-size: 50px">
    Sign in Charity Chess Games
</div>
<div class="container mt-5 mb-5">
    <div class="row d-flex align-items-center justify-content-center">
        <div class="col-md-7">
            <div class="card px-5 py-5"><span class="circle"><i class="fa fa-check"></i></span>
                <form action="<c:url value="/login"/>" method="post">
                    <div class="form-floating mb-3">
                        <input type="text" id="floatingInputUsername" class="form-control" placeholder="Username" name="username">
                        <label for="floatingInputUsername">Username</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password">
                        <label for="floatingPassword">Password</label>
                    </div>
                    <div class="text-center mt-4" style="color: red">
                        <c:if test="${error != null}">
                            <c:out value="${error}"/>
                        </c:if>
                    </div>
                    <button type="submit" class="btn btn-dark">Login</button>
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
