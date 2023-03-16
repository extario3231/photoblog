<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload</title>
</head>
<body>
    <h2>Upload photos</h2>
    <br>
    <form:form method="POST"  enctype="multipart/form-data" modelAttribute="uploadForm">
        <form:label path="description">Description</form:label>
        <br>
        <form:textarea rows="3" cols="20" path="description"/>
        <br>
        <input type="file" name="photos" multiple>
        <br>
        <button>Upload</button>
    </form:form>
</body>
</html>
