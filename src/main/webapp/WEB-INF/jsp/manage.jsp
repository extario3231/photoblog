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
        <c:forEach var="blogUser" items="${users}">
                Username: ${blogUser.username}<br>
                Description: ${blogUser.description}<br>
                Phone number: ${blogUser.phoneNumber}<br>
                Email: ${blogUser.email}<br>
        </c:forEach>
    </div>
</body>
</html>
