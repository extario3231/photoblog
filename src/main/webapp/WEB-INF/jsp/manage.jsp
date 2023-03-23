<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <h2>List of users</h2>
    <div style="text-align: center">
        <c:forEach var="user" items="${users}">
                Username: ${user.username}<br>
                Description: ${user.description}<br>
                Phone number: ${user.phoneNumber}<br>
                Email: ${user.email}<br>
        </c:forEach>
    </div>
</body>
</html>
