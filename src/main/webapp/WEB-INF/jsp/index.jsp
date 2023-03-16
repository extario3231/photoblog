<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>All Photos</h1>
    <a href="<c:url value="/upload"/>">Upload Photos</a>
    <br>
    <a href="<c:url value="/signup"/>">Sign up</a>
    <br>
    <a href="<c:url value="/login"/>">Log in</a>
    <br>
    <c:if test="${!empty username}">
        <h2>Welcome! ${username}</h2>
    </c:if>
    <br>
    <table>
        <c:forEach var="photo" items="${photos}">
            <tr>
                <td>
                    ${photo.content}
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
