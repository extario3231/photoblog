<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <h2>List of users</h2>
    <a id="btn" href="/user/add" style="right: 0">Add user</a>
    <div style="text-align: center">
        <table>
            <tr>
                <th>Username</th>
                <th>Description</th>
                <th>Phone number</th>
                <th>Email</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.description}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.email}</td>
                    <td>
                        <form action="/user/delete/${user.id}" method="post">
                            <button type="submit">Delete</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
