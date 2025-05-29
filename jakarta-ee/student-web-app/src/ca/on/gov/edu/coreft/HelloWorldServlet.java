package ca.on.gov.edu.coreft;
import org.apache.log4j.Logger;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorldServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(HelloWorldServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("hello world");
        response.setContentType("text/plain");
        response.getWriter().write("Hello, World Servlet!");
    }
}
