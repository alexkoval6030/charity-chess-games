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
            Games you can join
        </div>
        <div>
            <div class="container">
                <form action="<c:url value="/listAvailableGames"/>" method="post">
                    <table class="table table-dark table-striped">
                        <thead>
                            <tr>
                                <th scope="col">№</th>
                                <th scope="col">Game id</th>
                                <th scope="col">Game creator</th>
                                <th scope="col">Game creation date</th>
                                <th scope="col">Game status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="availableGame" items="${listAvailableGames}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>
                                        <a class="btn btn-dark" href="<c:url value="/viewParticipants?gameId=${availableGame.id}"/>"
                                           role="button">${availableGame.id}</a>
                                    </td>
                                    <td>${availableGame.creator.username}</td>
                                    <td>${availableGame.gameStatus.date}</td>
                                    <td>${availableGame.gameStatus.gameStatusName}</td>
                                    <td>
                                        <a class="btn btn-dark" href="<c:url value="/joinGame/${availableGame.id}"/>" role="button">Join</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <div style="text-align: center; font-size: 30px">
            You can use other options
        </div>
        <div class="container">
            <div class="btn-group d-grid d-md-flex" role="group" aria-label="Basic example">
                <a class="btn btn-dark" href="<c:url value="/homePage"/>" role="button">Homepage</a>
                <a class="btn btn-dark" href="<c:url value="/viewAllCreatedGames"/>" role="button">View all created games</a>
                <a class="btn btn-dark" href="<c:url value="/viewAllAttachedGames"/>" role="button">View a list of all the games you’ve joined</a>
                <a class="btn btn-dark" href="<c:url value="/myGames"/>" role="button">View all games</a>
                <a class="btn btn-dark" href="<c:url value="/homePage"/>" role="button">See the history of games</a>
            </div>
        </div>
    </body>
</html>
