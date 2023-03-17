<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Photo Details</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <h2>Uploaded by ${photo.uploader} on ${photo.uploadTime}</h2><br>
    <img src="data:image/jpg;base64, ${photo.content}" style="display: block; margin-left: auto; margin-right: auto"><br>

    <h2>Comments</h2>
    <c:forEach var="comment" items="${photo.comments}">
        ${comment}
    </c:forEach>
    <br>
    <form:form method="POST" modelAttribute="commentForm">
        <form:label path="comment">Write a comment</form:label>
        <br>
        <form:textarea rows="3" cols="20" path="comment"/>
        <br>
        <button>Comment</button>
    </form:form>
</body>
</html>
