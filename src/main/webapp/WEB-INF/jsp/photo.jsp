<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Photo Details</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <h2>Uploaded by ${photo.uploader} on ${photo.uploadTimeString}</h2><br>
    <img src="data:image/jpg;base64, ${photo.content}" style="display: block; margin-left: auto; margin-right: auto"><br>

    <c:if test="${fn:contains(userRoles, 'ADMIN')}">
        <form method="post" action="/photo/delete/${photo.id}">
            <button id="btn" style="right: 0;">Delete</button>
        </form>
    </c:if>

    <h2>Comments</h2>
    <hr>
    <c:forEach var="comment" items="${photo.comments}" varStatus="status">
        <p style="text-align: center">${comment.comment}</p>
        <c:if test="${!status.last}">
            <hr>
        </c:if>
    </c:forEach>
    <br>
    <form:form method="POST" modelAttribute="commentForm">
        <form:label path="comment">Write a comment</form:label>
        <br>
        <c:choose>
            <c:when test="${empty username}">
                <label>
                    <textarea rows="3" cols="20" disabled>Log in to comment</textarea>
                </label>
                <br>
                <button disabled>Comment</button>
            </c:when>
            <c:otherwise>
                <form:textarea rows="3" cols="20" path="comment"/>
                <br>
                <button>Comment</button>
            </c:otherwise>
        </c:choose>
    </form:form>
</body>
</html>
