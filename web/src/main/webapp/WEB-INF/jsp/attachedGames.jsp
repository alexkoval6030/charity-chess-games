<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <c:forEach var="attachedGame" items="${allAttachedGames}">
                        <tr>
                            <td>
                                <a class="btn btn-dark" href="<c:url value="/viewParticipants?gameId=${attachedGame.id}"/>"
                                   role="button">${attachedGame.id}</a>
                            </td>
                            <td>${attachedGame.gameStatus.date}</td>
                            <td>${attachedGame.gameStatus.gameStatusName}</td>
                            <td>${attachedGame.creator.username}</td>
                            <td><c:if test="${attachedGame.isCreatorWin == true}">
                                    You lose
                                </c:if>
                                <c:if test="${attachedGame.isCreatorWin == false}">
                                    You win
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <c:forEach begin="1" end="${pageCount}" var="index">
                            <c:if test="${index == page}">
                                <li class="page-item">
                                    <form action="<c:url value="/viewAllAttachedGames"/>">
                                        <input type="hidden" name="size" value="5" />
                                        <button name="page" value="${index - 1}" type="submit" class="btn btn-dark">${index}</button>
                                    </form>
                                </li>
                            </c:if>
                            <c:if test="${index != page}">
                                <li class="page-item">
                                    <form action="<c:url value="/viewAllAttachedGames"/>">
                                        <input type="hidden" name="size" value="5" />
                                        <button name="page" value="${index - 1}" type="submit" class="btn btn-outline-dark">${index}</button>
                                    </form>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
        <div style="text-align: center; font-size: 30px">
            You can use other options
        </div>
        <div class="container">
            <div class="btn-group d-grid d-md-flex justify-content-center" role="group" aria-label="Basic example">
                <form action="<c:url value="/homePage"/>">
                    <button type="submit" class="btn btn-dark">Homepage</button>
                </form>
                <form action="<c:url value="/viewAllCreatedGames"/>">
                    <input type="hidden" name="size" value="5" />
                    <button name="page" value="0"  type="submit" class="btn btn-dark">View all created games</button>
                </form>
                <form action="<c:url value="/viewAllAttachedGames"/>">
                    <input type="hidden" name="size" value="5" />
                    <button name="page" value="0"  type="submit" class="btn btn-dark">View a list of all the games you’ve joined</button>
                </form>
                <form action="<c:url value="/myGames"/>">
                    <input type="hidden" name="pageFirstTable" value="0" />
                    <input type="hidden" name="sizeFirstTable" value="5" />
                    <input type="hidden" name="pageSecondTable" value="0" />
                    <button name="sizeSecondTable" value="5"  type="submit" class="btn btn-dark">View all games</button>
                </form>
                <form action="<c:url value="/homePage"/>">
                    <button type="submit" class="btn btn-dark">See the history of games</button>
                </form>
            </div>
        </div>
    </body>
</html>