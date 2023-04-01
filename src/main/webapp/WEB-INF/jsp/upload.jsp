<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <h2>Upload photos</h2>
    <br>
    <form:form method="POST" cssStyle="text-align: center" enctype="multipart/form-data" modelAttribute="uploadForm">
        <form:label path="description">Description</form:label>
        <br>
        <form:textarea rows="3" cols="20" path="description"/>
        <br>
        <input type="file" name="photos" multiple>
        <br>
        <button type="submit">Upload</button>
    </form:form>
</body>
</html>
