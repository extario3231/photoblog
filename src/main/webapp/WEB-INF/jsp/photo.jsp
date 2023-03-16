<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>>
<html>
<head>
    <title>Photo Details</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <h2>Uploaded by ${user.name} ${photo.uploadTime}</h2><br>
        ${photo.content}<br>

    <h2>Comments</h2>
    <c:forEach var="comment" items="${photo.comments}">
        ${comment}
    </c:forEach>
    <br>
    <form:form method="POST" cssStyle="text-align: center" modelAttribute="commentForm">
        <form:label path="comment">Write a comment</form:label>
        <form:input type="text" path="comment"/>
        <button>Comment</button>
    </form:form>
</body>
</html>
