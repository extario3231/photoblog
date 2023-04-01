<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
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
    <p>Name: <security:authentication property="principal.username"/></p>
    <br>
    <form action="/profile/edit" method="post" style="text-align: center">
        <label>Description:</label>
        <br>
        <label>
            <textarea name="description" cols="30" rows="3">${description}</textarea>
        </label>
        <br>
        <button type="submit">OK</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</body>
</html>
