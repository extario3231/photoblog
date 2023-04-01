<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign up</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <h2>Sign up</h2>
    <br>
    <form:form method="POST" cssStyle="text-align: center" modelAttribute="signupForm">
        <form:label path="username">Username</form:label>
        <br>
        <form:input path="username" type="text"/>
        <br>
        <form:label path="password">Password</form:label>
        <br>
        <form:input path="password" type="password"/>
        <br>
        <form:label path="phoneNumber">Phone Number</form:label>
        <br>
        <form:input path="phoneNumber" type="text"/>
        <br>
        <form:label path="email">Email</form:label>
        <br>
        <form:input path="email" type="text"/>
        <form:input path="roles" value="USER" hidden="true"/>
        <br>
        <button type="submit">Sign up</button>
    </form:form>
</body>
</html>
