<!DOCTYPE html>
<html>
<head>
    <title>My Profile</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    <a id="btn" href="/profile/edit" style="left: 0;">Edit</a>
    <br>
    <h2>Photos</h2>
    <hr>
    <c:choose>
        <c:when test="${empty photos}">
            <p>No photos uploaded yet</p>
        </c:when>
        <c:otherwise>
            <div class="photo-grid">
                <c:forEach var="photo" items="${photos}" varStatus="status">
                    <div class="inline-block align-middle">
                        <a href="<c:url value="/photo/${photo.id}"/>">
                            <img src="data:image/jpg;base64, ${photo.content}" alt="${photo.name}" class="max-w-full max-h-full">
                        </a>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
