package com.azure.sample;

import java.io.IOException;
import java.sql.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.logging.Logger;

public class AddStudentServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AddStudentServlet.class.getName());
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String major = request.getParameter("major");
        boolean success = false;
        String errorMsg = null;
        try {
            logger.info("Starting to add student: name=" + name + ", email=" + email + ", major=" + major);
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/StudentDB");
            try (Connection conn = ds.getConnection();
                 PreparedStatement ps = conn.prepareStatement("INSERT INTO student_profiles (name, email, major) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, major);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    success = true;
                    logger.info("Student added successfully, sending email to: " + email);
                    // Send email notification
                    sendEmail(email, name);
                } else {
                    logger.warning("No rows inserted for student: " + name);
                }
            }
        } catch (Exception e) {
            logger.severe("Error adding student: " + e.getMessage());
            errorMsg = e.getMessage();
        }
        if (success) {
            logger.info("Redirecting to HelloServlet after successful add.");
            response.setContentType("text/plain");
            response.getWriter().write("Studnet added successfully.");
        } else {
            logger.warning("Add student failed: " + errorMsg);
            request.setAttribute("errorMsg", errorMsg != null ? errorMsg : "Failed to add student.");
            request.getRequestDispatcher("/add_student_profile.jsp").forward(request, response);
        }
    }

    private void sendEmail(String to, String name) throws Exception {
        logger.info("Preparing to send email to: " + to);
        // Lookup mail session from JNDI (configured in server.xml)
        Context ctx = new InitialContext();
        Session session = (Session) ctx.lookup("java:comp/env/mail/StudentMailSession");
        Message msg = new MimeMessage(session);
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
        msg.setSubject("Welcome, " + name + "!");
        msg.setText("Dear " + name + ",\n\nYour student profile has been created successfully.\n\nRegards,\nAdmin");
        Transport.send(msg);
        logger.info("Email sent to: " + to);
    }
}
