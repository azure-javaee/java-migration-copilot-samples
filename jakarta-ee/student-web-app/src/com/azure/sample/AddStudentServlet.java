package com.azure.sample;

import com.azure.sample.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;

public class AddStudentServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddStudentServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String major = request.getParameter("major");
        boolean success = false;
        String errorMsg = null;
        SqlSession session = null;
        try {
            logger.info("Starting to add student: name=" + name + ", email=" + email + ", major=" + major);
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            Map<String, Object> params = new HashMap<>();
            params.put("name", name);
            params.put("email", email);
            params.put("major", major);
            int rows = session.insert("com.azure.sample.StudentMapper.addStudent", params);
            if (rows > 0) {
                success = true;
                logger.info("Student added successfully, sending email to: " + email);
                session.commit();
                // Send email notification
                sendEmail(email, name);
            } else {
                logger.warn("No rows inserted for student: " + name);
            }
        } catch (Exception e) {
            logger.error("Error adding student: " + e.getMessage());
            errorMsg = e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        if (success) {
            logger.info("Redirecting to HelloServlet after successful add.");
            response.setContentType("text/plain");
            response.getWriter().write("Student added successfully.");
        } else {
            logger.warn("Add student failed: " + errorMsg);
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
