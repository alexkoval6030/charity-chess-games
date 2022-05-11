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
        <form action="<c:url value="/userPayments"/>" method="get">
            <div class="row">
                <div class="col">
                    <select class="form-select" name="paymentTransactionType">
                        <option selected>
                            <c:if test="${paymentTransactionType != null || paymentTransactionType != ''}">
                                ${paymentTransactionType}
                            </c:if>
                            <c:if test="${paymentTransactionType == null}">
                                Payment type
                            </c:if>
                            </option>
                        <option value=""></option>
                        <option value="${transactionType1}">${transactionType1}</option>
                        <option value="${transactionType2}">${transactionType2}</option>
                        <option value="${transactionType3}">${transactionType3}</option>
                        <option value="${transactionType4}">${transactionType4}</option>
                        <option value="${transactionType5}">${transactionType5}</option>
                        <option value="${transactionType6}">${transactionType6}</option>
                    </select>
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
                    <th scope="col">Payment id</th>
                    <th scope="col">Payment date</th>
                    <th scope="col">Payment type</th>
                    <th scope="col">Payment sum</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="paymentDto" items="${paymentDtoPage}">
                    <tr>
                        <td>${paymentDto.id}</td>
                        <td>${paymentDto.paymentDate}</td>
                        <td>${paymentDto.paymentTransactionType}</td>
                        <td>${paymentDto.paymentSum}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <c:forEach begin="1" end="${pageCount}" var="index">
                        <c:if test="${index == page}">
                            <li class="page-item">
                                <form action="<c:url value="/userPayments"/>">
                                    <c:if test="${paymentTransactionType != null || paymentTransactionType != ''}">
                                        <input type="hidden" name="creatorUsername" value="${paymentTransactionType}" />
                                    </c:if>
                                    <c:if test="${fromDateString != null || fromDateString != ''}">
                                        <input type="hidden" name="fromDateString" value="${fromDateString}" />
                                    </c:if>
                                    <c:if test="${toDateString != null || toDateString != ''}">
                                        <input type="hidden" name="toDateString" value="${toDateString}" />
                                    </c:if>
                                    <c:if test="${minimum != null || minimum != ''}">
                                        <input type="hidden" name="minimum" value="${minimum}" />
                                    </c:if>
                                    <c:if test="${maximum != null || maximum != ''}">
                                        <input type="hidden" name="maximum" value="${maximum}" />
                                    </c:if>
                                    <input type="hidden" name="size" value="5" />
                                    <button name="page" value="${index - 1}" type="submit" class="btn btn-dark">${index}</button>
                                </form>
                            </li>
                        </c:if>
                        <c:if test="${index != page}">
                            <li class="page-item">
                                <form action="<c:url value="/userPayments"/>">
                                    <c:if test="${paymentTransactionType != null}">
                                        <input type="hidden" name="paymentTransactionType" value="${paymentTransactionType}" />
                                    </c:if>
                                    <c:if test="${fromDateString != null}">
                                        <input type="hidden" name="fromDateString" value="${fromDateString}" />
                                    </c:if>
                                    <c:if test="${toDateString != null}">
                                        <input type="hidden" name="toDateString" value="${toDateString}" />
                                    </c:if>
                                    <c:if test="${minimum != null}">
                                        <input type="hidden" name="minimum" value="${minimum}" />
                                    </c:if>
                                    <c:if test="${maximum != null}">
                                        <input type="hidden" name="maximum" value="${maximum}" />
                                    </c:if>
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
