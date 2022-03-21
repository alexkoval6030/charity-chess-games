package by.kovalenko.filters;

import by.kovalenko.util.UserRole;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("AuthorizationFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (isPrivatePage(httpServletRequest)) {
            Object role = httpServletRequest.getSession().getAttribute("ROLE");
            if (role != null && role.toString().equals(UserRole.USER.toString())) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/form/login");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isPrivatePage(HttpServletRequest request) {
        return !request.getRequestURI().contains("login")
                && !request.getRequestURI().contains("registration")
                && !request.getRequestURI().contains("static");
    }

    @Override
    public void destroy() {
    }
}
