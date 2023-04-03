<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload History</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <h2>Photos Uploaded</h2>

    <table style="text-align: center">
        <tr>
            <th>Photo name</th>
            <th>Upload time</th>
        </tr>

        <c:forEach var="photo" items="${photos}">
            <tr>
                <td><a href="/photo/${photo.id}">${photo.name}</a></td>
                <td>${photo.uploadTimeString}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
