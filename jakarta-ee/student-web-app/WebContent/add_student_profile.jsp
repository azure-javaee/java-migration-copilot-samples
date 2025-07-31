<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>Add Student Profile</title>
</head>
<body>
    <h1>Add Student Profile</h1>
    <form action="addStudent" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="major">Major:</label>
        <input type="text" id="major" name="major" required><br><br>
        <input type="submit" value="Add Student">
    </form>
    <p><a href="studentProfileList">Back to Student List</a></p>
</body>
</html>
