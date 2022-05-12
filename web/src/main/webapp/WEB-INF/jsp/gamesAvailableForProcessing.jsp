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
        Use sorting to the desired fields
    </div>
    <div class="container mt-5 mb-5">
        <form action="<c:url value="/gamesAvailableForProcessing"/>" method="get">
            <div class="row">
                <div class="col">
                    <input type="text" id="creatorUsername" class="form-control"
                           placeholder="Creator username" name="creatorUsername"
                    <c:if test="${creatorUsername != null || creatorUsername != ''}">
                           value="${creatorUsername}"
                    </c:if>>
                </div>
                <div class="col">
                    <input type="text" id="fromWhatDate" class="form-control"
                           placeholder="From date" name="fromDateString"
                    <c:if test="${fromDateString != null || fromDateString != ''}">
                           value="${fromDateString}"
                    </c:if>>
                </div>
                <div class="col">
                    <input type="text" id="toWhatDate" class="form-control"
                           placeholder="To date" name="toDateString"
                    <c:if test="${toDateString != null || toDateString != ''}">
                           value="${toDateString}"
                    </c:if>>
                </div>
                <div class="col">
                    <input type="text" id="minimum" class="form-control"
                           placeholder="Minimum bet" name="minimum"
                    <c:if test="${minimum != null || minimum != ''}">
                           value="${minimum}"
                    </c:if>>
                </div>
                <div class="col">
                    <input type="text" id="maximum" class="form-control"
                           placeholder="Maximum bet" name="maximum"
                    <c:if test="${maximum != null || maximum != ''}">
                           value="${maximum}"
                    </c:if>>
                </div>
                <div class="col">
                    <input type="hidden" name="size" value="5" />
                    <input type="hidden" name="page" value="0" />
                    <button type="submit" class="btn btn-dark">Filter</button>
                </div>
            </div>
        </form>
    </div>
    <div>
        <div class="container">
            <table class="table table-dark table-striped">
                <thead>
                <tr>
                    <th scope="col">Game id</th>
                    <th scope="col">Game creator</th>
                    <th scope="col">Creator bet</th>
                    <th scope="col">Game creation date</th>
                    <th scope="col">Game status</th>
                    <th scope="col">Choose result</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="paymentDto" items="${gameDtoPage}">
                    <tr>
                        <td>
                            <a class="btn btn-dark"
                               href="<c:url value="/viewParticipants?gameId=${paymentDto.id}"/>"
                               role="button">${paymentDto.id}
                            </a>
                        </td>
                        <td>${paymentDto.creator.username}</td>
                        <td>${paymentDto.creatorStake}</td>
                        <td>${paymentDto.gameStatus.date}</td>
                        <td>${paymentDto.gameStatus.gameStatusName}</td>
                        <td>
                            <form action="<c:url value="/resultCreatorWin"/>">
                                <div class="input-group">
                                    <input type="hidden" name="gameId" value="${paymentDto.id}"/>
                                    <input type="hidden" name="result" value="true"/>
                                    <button type="submit" class="btn btn-success">Creator win</button>
                                </div>
                            </form>
                            <form action="<c:url value="/resultCreatorLose"/>">
                                <div class="input-group">
                                    <input type="hidden" name="gameId" value="${paymentDto.id}"/>
                                    <input type="hidden" name="result" value="false"/>
                                    <button type="submit" class="btn btn-danger">Creator lose</button>
                                </div>
                            </form>
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
                                <form action="<c:url value="/gamesAvailableForProcessing"/>">
                                    <c:if test="${creatorUsername != null || creatorUsername != ''}">
                                        <input type="hidden" name="creatorUsername"
                                               value="${creatorUsername}"/>
                                    </c:if>
                                    <c:if test="${fromDateString != null || fromDateString != ''}">
                                        <input type="hidden" name="fromDateString"
                                               value="${fromDateString}"/>
                                    </c:if>
                                    <c:if test="${toDateString != null || toDateString != ''}">
                                        <input type="hidden" name="toDateString"
                                               value="${toDateString}"/>
                                    </c:if>
                                    <c:if test="${minimum != null || minimum != ''}">
                                        <input type="hidden" name="minimum" value="${minimum}"/>
                                    </c:if>
                                    <c:if test="${maximum != null || maximum != ''}">
                                        <input type="hidden" name="maximum" value="${maximum}"/>
                                    </c:if>
                                    <input type="hidden" name="size" value="5"/>
                                    <button name="page" value="${index - 1}" type="submit"
                                            class="btn btn-dark">${index}
                                    </button>
                                </form>
                            </li>
                        </c:if>
                        <c:if test="${index != page}">
                            <li class="page-item">
                                <form action="<c:url value="/gamesAvailableForProcessing"/>">
                                    <c:if test="${creatorUsername != null}">
                                        <input type="hidden" name="creatorUsername"
                                               value="${creatorUsername}"/>
                                    </c:if>
                                    <c:if test="${fromDateString != null}">
                                        <input type="hidden" name="fromDateString"
                                               value="${fromDateString}"/>
                                    </c:if>
                                    <c:if test="${toDateString != null}">
                                        <input type="hidden" name="toDateString"
                                               value="${toDateString}"/>
                                    </c:if>
                                    <c:if test="${minimum != null}">
                                        <input type="hidden" name="minimum" value="${minimum}"/>
                                    </c:if>
                                    <c:if test="${maximum != null}">
                                        <input type="hidden" name="maximum" value="${maximum}"/>
                                    </c:if>
                                    <input type="hidden" name="size" value="5"/>
                                    <button name="page" value="${index - 1}" type="submit"
                                            class="btn btn-outline-dark">${index}
                                    </button>
                                </form>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </div>
</body>
</html>
