<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <h1>All Photos</h1>
    <a id="btn" style="right: 3rem" href="<c:url value="/signup"/>">Sign up</a>
    <a id="btn" style="right: 0" href="<c:url value="/login"/>">Log in</a>
    <c:if test="${!empty username}">
        <a id="btn" style="right: 6.4rem" href="<c:url value="/blog/upload"/>">Upload Photos</a>
        <a id="btn" style="right: 12.6rem" href="<c:url value="/profile"/>">My Profile</a>
        <br>
        <p style="top: 0; position: absolute; margin: unset; right: 17.6rem;">Welcome, ${username}</p>
    </c:if>
    <br>
    <table>
        <c:forEach var="photo" items="${photos}" varStatus="status">
            <tr>
                <td>
                    <a href="<c:url value="/blog/photo/${status.count}"/>"><img src="data:image/jpg;base64, ${photo}" alt="${status.count}"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
