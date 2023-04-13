<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h2>Photos</h2>
    <hr>
    <c:choose>
        <c:when test="${empty photos}">
            <p>No photos uploaded yet</p>
        </c:when>
        <c:otherwise>
            <div class="photo-grid">
                <c:forEach var="photo" items="${photos}">
                    <a href="/photo/${photo.id}">
                        <img src="data:image/jpg;base64, ${photo.content}" alt="${photo.name}">
                    </a>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
