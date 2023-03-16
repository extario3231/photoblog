<!DOCTYPE html>
<html>
<head>
    <title>${username}'s Profile</title>
    <link href="<c:url value="/style.css"/>" rel="stylesheet" type="text/css">
    <style>
        p {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>${username}'s Profile</h1>
    <br>
    <p>Name: ${username}</p>
    <br>
    <p>Description: ${description}</p>
    <br>
    <h2>Photos: ${photos}</h2>
</body>
</html>
