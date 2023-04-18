<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<h2>Add user</h2>
<form:form method="POST" modelAttribute="userForm">
    <form:label path="username">Username</form:label>
    <br>
    <form:input path="username" type="text"/>
    <br>
    <form:label path="password">Password</form:label>
    <br>
    <form:input path="password" type="password"/>
    <br>
    <form:label path="email">Email</form:label>
    <br>
    <form:input path="email" type="text"/>
    <br>
    <form:label path="phoneNumber">Phone number</form:label>
    <br>
    <form:input path="phoneNumber" type="text"/>
    <br>
    <form:checkboxes path="roles" items="${roles}"/>
    <br>
    <button>Create</button>
</form:form>
</body>
</html>
