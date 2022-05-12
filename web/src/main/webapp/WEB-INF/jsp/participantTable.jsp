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
            <div class="btn-group d-grid d-md-flex justify-content-center" role="group"
                 aria-label="Basic example">
                <form action="<c:url value="/homePage"/>">
                    <button type="submit" class="btn btn-dark">Homepage</button>
                </form>
                <form action="<c:url value="/viewAllCreatedGames"/>">
                    <input type="hidden" name="size" value="5" />
                    <button name="page" value="0"  type="submit" class="btn btn-dark">
                        View all created games
                    </button>
                </form>
                <form action="<c:url value="/viewAllAttachedGames"/>">
                    <input type="hidden" name="size" value="5" />
                    <button name="page" value="0"  type="submit" class="btn btn-dark">
                        View a list of all the games you’ve joined
                    </button>
                </form>
                <form action="<c:url value="/myGames"/>">
                    <input type="hidden" name="pageFirstTable" value="0" />
                    <input type="hidden" name="sizeFirstTable" value="5" />
                    <input type="hidden" name="pageSecondTable" value="0" />
                    <button name="sizeSecondTable" value="5"  type="submit" class="btn btn-dark">
                        View all games
                    </button>
                </form>
                <form action="<c:url value="/userPayments"/>">
                    <input type="hidden" name="size" value="5" />
                    <button name="page" value="0"  type="submit" class="btn btn-dark">
                        See payment history
                    </button>
                </form>
            </div>
        </div>
    </body>
</html>
