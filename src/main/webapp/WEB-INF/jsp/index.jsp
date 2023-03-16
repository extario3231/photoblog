<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h2>All Photos</h2>
    <a href="<c:url value="/blog/upload"/>">Upload Photos</a>
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
