<!DOCTYPE html>
<html>
<head>
    <title>Upload History</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <h2>Photos Uploaded</h2>

    <table style="text-align: center">
        <tr>
            <th>Photo name</th>
            <th>Uploader</th>
            <th>Upload time</th>
        </tr>

        <c:forEach var="photo" items="${photos}">
            <tr>
                <td><a href="/photo/${photo.id}">${photo.name}</a></td>
                <td>${photo.uploader}</td>
                <td>${photo.uploadTimeString}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
