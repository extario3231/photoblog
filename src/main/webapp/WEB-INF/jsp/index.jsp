<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h2>All Photos</h2>

    <c:forEach var="photo" items="${photos}">
        <table>
            <tr>
                <td>${photo.content}</td>
            </tr>
        </table>
    </c:forEach>
</body>
</html>
