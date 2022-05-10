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
                <input type="text" id="paymentTransactionType" class="form-control"
                       placeholder="Payment type" name="paymentTransactionType"
                <c:if test="${paymentTransactionType != null || paymentTransactionType != ''}">
                       value="${paymentTransactionType}"
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
</body>
</html>
