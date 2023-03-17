<!DOCTYPE html>
<html>
<head>
    <title>${username}'s Profile</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
    <style>
        p {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>${username}'s Profile</h1>
    <br>
    <p>Name: ${username}</p>
    <br>
    <p>Description: ${description}</p>
    <br>
    <h2>Photos:</h2>
    <hr>
    <c:forEach var="photo" items="${photos}">
        <img src="data:image/jpg;base64, ${photo}">
        <br>
    </c:forEach>
</body>
</html>
