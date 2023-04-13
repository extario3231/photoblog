<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <h1>All Photos</h1>
    <security:authorize access="hasAnyRole('USER', 'ADMIN')">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit" style="right: 0; top: 0; position: absolute">Log out</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </form>
        <a id="btn" style="right: 4rem" href="<c:url value="/upload"/>">Upload Photos</a>
        <a id="btn" style="right: 10.3rem" href="<c:url value="/profile"/>">My Profile</a>
        <p style="top: 0; position: absolute; margin: unset; right: 15rem;">Welcome, <security:authentication property="principal.username"/></p>
    </security:authorize>
    <security:authorize access="!isAuthenticated()">
        <a id="btn" style="right: 3rem" href="<c:url value="/signup"/>">Sign up</a>
        <a id="btn" style="right: 0" href="<c:url value="/login"/>">Log in</a>
    </security:authorize>
    <br>
    <div class="photo-grid">
        <c:forEach var="photo" items="${photos}" varStatus="status">
            <a href="<c:url value="/photo/${photo.id}"/>">
                <img src="data:image/jpg;base64, ${photo.content}" alt="${photo.name}">
            </a>
        </c:forEach>
    </div>
</body>
</html>
