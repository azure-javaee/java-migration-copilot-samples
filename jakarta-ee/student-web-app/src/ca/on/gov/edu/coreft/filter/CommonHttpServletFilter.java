package ca.on.gov.edu.coreft.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CommonHttpServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter initialization logic, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;

            // Extracting the client IP from custom headers
            String xForwardedFor = httpServletRequest.getHeader("X-Forwarded-For");
            String xClientIP = httpServletRequest.getHeader("X-Client-IP");

            String realClientIP = null;

            if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
                // X-Forwarded-For header can contain a comma-separated list of IPs
                realClientIP = xForwardedFor.split(",")[0].trim(); // Take the first IP
            } else if (xClientIP != null && !xClientIP.isEmpty()) {
                realClientIP = xClientIP; // Use X-Client-IP directly if available
            } else {
                // Fallback to the remote address as last resort
                realClientIP = request.getRemoteAddr();
            }

            // Add the real client IP as a request attribute for downstream usage
            httpServletRequest.setAttribute("RealClientIP", realClientIP);
        }

        // Proceed with the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup logic, if needed
    }

}
