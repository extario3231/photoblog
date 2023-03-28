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
        <a id="btn" style="right: 0" href="<c:url value="/logout"/>">Log out</a>
        <a id="btn" style="right: 3.5rem" href="<c:url value="/upload"/>">Upload Photos</a>
        <a id="btn" style="right: 9.7rem" href="<c:url value="/profile"/>">My Profile</a>
        <p style="top: 0; position: absolute; margin: unset; right: 14.5rem;">Welcome, ${username}</p>
    </security:authorize>
    <security:authorize access="!isAuthenticated()">
        <a id="btn" style="right: 3rem" href="<c:url value="/signup"/>">Sign up</a>
        <a id="btn" style="right: 0" href="<c:url value="/login"/>">Log in</a>
    </security:authorize>
    <br>
    <table>
        <c:forEach var="photo" items="${photos}">
            <tr>
                <td>
                    <a href="<c:url value="/photo/${photo.id}"/>">
                        <img src="data:image/jpg;base64, ${photo.content}" alt="${photo.id}" height="300" width="450">
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
