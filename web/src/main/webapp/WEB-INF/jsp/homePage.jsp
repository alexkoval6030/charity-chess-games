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
            Welcome to Charity Chess Games
        </div>
        <div class="container">
            <div class="card" style="width: 14rem;">
                <img src="img/createGame.jpg" class="card-img-top" alt="create game">
                <div class="card-body">
                    <h5 class="card-title">Create game</h5>
                    <p class="card-text">You can create a new game</p>
                    <a href="<c:url value="/createGame"/>" class="btn btn-primary">Create</a>
                </div>
            </div>
            <div class="card" style="width: 14rem;">
                <img src="img/createGame.jpg" class="card-img-top" alt="create game">
                <div class="card-body">
                    <h5 class="card-title">My game</h5>
                    <p class="card-text">You can watch your games</p>
                    <a href="<c:url value="/myGames"/>" class="btn btn-primary">My games</a>
                </div>
            </div>
        </div>
    </body>
</html>
