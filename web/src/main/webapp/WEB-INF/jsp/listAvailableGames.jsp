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
                <form action="<c:out value="/joinAvailableGames"/>" method="post">
                    <table class="table table-dark table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Game id</th>
                                <th scope="col">Game creator</th>
                                <th scope="col">Game creation date</th>
                                <th scope="col">Game status</th>
                                <th scope="col">Join</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="listAvailableGames" items="${listAvailableGames}">
                                <tr>
                                    <td>${listAvailableGames.id}</td>
                                    <td>${listAvailableGames.creator.username}</td>
                                    <td>${listAvailableGames.gameStatus.date}</td>
                                    <td>${listAvailableGames.gameStatus.gameStatusName}</td>
                                    <td>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
                <div class="d-grid gap-2">
                    <button class="btn btn-dark btn-lg" type="submit">Join selected games</button>
                </div>
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
