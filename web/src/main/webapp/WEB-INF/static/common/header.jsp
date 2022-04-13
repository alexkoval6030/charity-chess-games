<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<footer>
    <nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand">
                <img src="img/chess.png" height="65" width="86" alt="chess">
                Charity Chess Games</a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="<c:url value="/homePage"/>">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="https://github.com/JD2-86/pet-project-kovalenko">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="<c:url value="/partners"/>">Partners</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Login/Registration
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="<c:url value="/login"/>">Login</a>
                            </li>
                            <li><a class="dropdown-item" href="<c:url value="/registration"/>">Registration</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="collapse navbar-collapse navbar-light justify-content-end" id="right-menu">
                <ul class="nav nav-pills">
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true"><c:out
                                value="${sessionScope.user.firstname} ${sessionScope.user.lastname}"/></a>
                    </li>
                    <li class="nav-item">
                        <a href="<c:url value="/logout"/>"
                           class="btn btn-primary" tabindex="-1" role="button" aria-disabled="true">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</footer>
