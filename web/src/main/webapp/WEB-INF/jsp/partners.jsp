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
