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
    Account replenishment
</div>
<div class="container mt-5 mb-5">
    <div class="row d-flex align-items-center justify-content-center">
        <div class="col-md-7">
            <div class="card px-5 py-5"><span class="circle"><i class="fa fa-check"></i></span>
                <form action="<c:url value="/addFunds"/>" method="post">
                    <div class="form-floating mb-3">
                        <input type="text" id="floatingInputFirstName" class="form-control"
                               placeholder="First name" name="firstname">
                        <label for="floatingInputFirstName">First name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" id="floatingInputLastName" class="form-control"
                               placeholder="Last name"
                               name="lastname">
                        <label for="floatingInputLastName">Last name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" id="floatingInputCardNumber" class="form-control"
                               placeholder="Card number"
                               name="cardNumber">
                        <label for="floatingInputCardNumber">Card number</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" id="floatingInputCardExpirationDate" class="form-control"
                               placeholder="Card expiration date" name="cardExpirationDate">
                        <label for="floatingInputCardExpirationDate">Card expiration date</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" id="floatingInputDepositAmount" class="form-control"
                               placeholder="Deposit Amount" name="depositAmount">
                        <label for="floatingInputDepositAmount">Deposit Amount</label>
                    </div>
                    <div class="text-center mt-4" style="color: red">
                        <c:if test="${error != null}">
                            <c:out value="${error}"/>
                        </c:if>
                    </div>
                    <button type="submit" class="btn btn-dark">Replenish</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
