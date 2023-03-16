<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Log in</title>
</head>
<body>
    <h2>Log in</h2>
    <br>
    <form:form method="POST" modelAttribute="loginForm">
        <form:label path="username">Username</form:label>
        <br>
        <form:input path="username" type="text"/>
        <br>
        <form:label path="password">Password</form:label>
        <br>
        <form:input path="password" type="text"/>
        <br>
        <button>Log in</button>
    </form:form>
</body>
</html>
