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
            Participants table - game(${game.id})
        </div>
        <div>
            <div class="container">
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th scope="col">№</th>
                            <th scope="col">Lastname</th>
                            <th scope="col">Firstname</th>
                            <th scope="col">Username</th>
                            <th scope="col">Email</th>
                        </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="5">CREATOR</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>${game.creator.lastname}</td>
                        <td>${game.creator.firstname}</td>
                        <td>${game.creator.username}</td>
                        <td>${game.creator.email}</td>
                    </tr>
                    <tr>
                        <td colspan="5">PARTICIPANTS</td>
                    </tr>

                        <c:forEach var="participant" items="${game.users}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${participant.lastname}</td>
                                <td>${participant.firstname}</td>
                                <td>${participant.username}</td>
                                <td>${participant.email}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
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
