<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <h1>All Photos</h1>
    <a id="btn" style="right: 110px" href="<c:url value="/blog/upload"/>">Upload Photos</a>
    <a id="btn" style="right: 50px" href="<c:url value="/signup"/>">Sign up</a>
    <a id="btn" style="right: 0" href="<c:url value="/login"/>">Log in</a>
    <c:if test="${!empty username}">
        <a id="btn" style="right: 210px" href="<c:url value="/myprofile"/>">My Profile</a>
        <br>
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
