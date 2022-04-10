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
            The games you created
        </div>
        <div>
            <div class="container">
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Game id</th>
                            <th scope="col">Game creation date</th>
                            <th scope="col">Game status</th>
                            <th scope="col">Who created this game</th>
                            <th scope="col">Result of the game</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="allCreatedGames" items="${allCreatedGames}">
                            <tr>
                                <td>${allCreatedGames.id}</td>
                                <td>${allCreatedGames.gameStatus.date}</td>
                                <td>${allCreatedGames.gameStatus.gameStatusName}</td>
                                <td>${allCreatedGames.creator.username}</td>
                                <td><c:if test="${allCreatedGames.isCreatorWin == true}">
                                        You win
                                    </c:if>
                                    <c:if test="${allCreatedGames.isCreatorWin == false}">
                                        You lose
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div style="text-align: center; font-size: 50px">
            Games in which you participate
        </div>
        <div>
            <div class="container">
                <table class="table table-dark table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Game id</th>
                        <th scope="col">Game creation date</th>
                        <th scope="col">Game status</th>
                        <th scope="col">Who created this game</th>
                        <th scope="col">Result of the game</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="allAttachedGames" items="${allAttachedGames}">
                        <tr>
                            <td>${allAttachedGames.id}</td>
                            <td>${allAttachedGames.gameStatus.date}</td>
                            <td>${allAttachedGames.gameStatus.gameStatusName}</td>
                            <td>${allAttachedGames.creator.username}</td>
                            <td><c:if test="${allCreatedGames.isCreatorWin == true}">
                                    You lose
                                </c:if>
                                <c:if test="${allCreatedGames.isCreatorWin == false}">
                                    You win
                                </c:if>
                            </td>
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
                <a class="btn btn-outline-dark" href="<c:url value="/homePage"/>" role="button">Homepage</a>
                <a class="btn btn-outline-dark" href="<c:url value="/viewAllCreatedGames"/>" role="button">View all created games</a>
                <a class="btn btn-outline-dark" href="<c:url value="/homePage"/>" role="button">View a list of all the games you’ve joined</a>
                <a class="btn btn-outline-dark" href="<c:url value="/myGames"/>" role="button">View all games</a>
                <a class="btn btn-outline-dark" href="<c:url value="/homePage"/>" role="button">See the history of games</a>
            </div>
        </div>
    </body>
</html>