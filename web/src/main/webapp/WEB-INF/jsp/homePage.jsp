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
        <div class="row row-cols-1 row-cols-4 g-5">
            <div class="col">
                <div class="card">
                    <img src="img/createGame.jpg" class="card-img-top" width="144" height="259" alt="create game">
                    <div class="card-body">
                        <h5 class="card-title">Create game</h5>
                        <p class="card-text">You can create a new game</p>
                        <a href="<c:url value="/createGame"/>" class="btn btn-dark">Create</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img src="img/queen.jfif" class="card-img-top" width="144" height="259" alt="My games">
                    <div class="card-body">
                        <h5 class="card-title">My games</h5>
                        <p class="card-text">You can watch your games</p>
                        <a href="<c:url value="/myGames"/>" class="btn btn-dark">My games</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img src="img/horse.jfif" class="card-img-top" width="144" height="259" alt="Join the game">
                    <div class="card-body">
                        <h5 class="card-title">Join the game</h5>
                        <p class="card-text">You can join existing games</p>
                        <a href="<c:url value="/listAvailableGames"/>" class="btn btn-dark">Join</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img src="img/pawn.jfif" class="card-img-top" width="144" height="259" alt="My Game Story">
                    <div class="card-body">
                        <h5 class="card-title">My Game Story</h5>
                        <p class="card-text">You can look at the history of the games played</p>
                        <a href="<c:url value="/myGames"/>" class="btn btn-dark">My Game Story</a>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </body>
</html>
