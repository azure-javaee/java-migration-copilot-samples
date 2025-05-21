<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.azure.sample.StudentProfile" %>
<html>
<head>
    <title>Open Liberty Ant Project</title>
</head>
<body>
    <h1>Student Profiles</h1>
    <p><a href="add_student_profile.jsp">Add New Student</a></p>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Major</th>
        </tr>
        <%
            java.util.List students = (java.util.List) request.getAttribute("students");
            if (students != null && !students.isEmpty()) {
                for (Object obj : students) {
                    StudentProfile s = (StudentProfile) obj;
        %>
        <tr>
            <td><%= s.getId() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getEmail() %></td>
            <td><%= s.getMajor() %></td>
        </tr>
        <%      }
            } else { %>
        <tr><td colspan="4">No student profiles found.</td></tr>
        <% } %>
    </table>
</body>
</html>
