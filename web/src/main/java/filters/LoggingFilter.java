package filters;

import by.kovalenko.entity.UserEntity;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalTime;

@WebFilter(filterName = "LoggingFilter")
public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("LoggingFilter");
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            try {
                chain.doFilter(request, response);
            } finally {
                auditHttpRequest((HttpServletRequest) request);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private void auditHttpRequest(HttpServletRequest request) {
        print("URL: " + request.getRequestURL().toString());
        HttpSession httpSession = request.getSession();
        if (httpSession != null && httpSession.getAttribute("user") != null) {
            UserEntity userEntity = (UserEntity) httpSession.getAttribute("user");
            print(String.format("Username: %s", userEntity.getUsername()));
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                print(String.format("Cookie %s = %s", cookie.getName(), cookie.getValue()));
            }
        }
    }

    private void print(String text) {
        System.out.printf("[Audit] - %s - %s\n", LocalTime.now(), text);
    }

    @Override
    public void destroy() {
    }
}
