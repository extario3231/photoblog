<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Profile</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
    <style>
        p {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>My Profile</h1>
    <br>
    <a id="btn" href="/upload/history" style="right: 0">Upload History</a>
    <p>Name: <security:authentication property="principal.username"/></p>
    <br>
    <p>Description: ${description}</p>
    <a id="btn" href="/profile/edit">Edit</a>
    <br>
    <h2>Photos:</h2>
    <hr>
    <c:forEach var="photo" items="${photos}">
        <img src="data:image/jpg;base64, ${photo.content}" alt="${photo.name}">
        <br>
    </c:forEach>
</body>
</html>
