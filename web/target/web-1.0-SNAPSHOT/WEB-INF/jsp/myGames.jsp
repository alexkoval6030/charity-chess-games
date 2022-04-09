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
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="allCreatedGames" items="${allCreatedGames}">
                            <tr>
                                <td>${allCreatedGames.id}</td>
                                <td>${allCreatedGames.gameStatus.date}</td>
                                <td>${allCreatedGames.gameStatus.gameStatusName}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div></div>
    </body>
</html>