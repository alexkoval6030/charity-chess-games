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
    <div>
        <img class="rounded mx-auto d-block img-thumbnail" src="img/partners.jpg" alt="partners">
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
