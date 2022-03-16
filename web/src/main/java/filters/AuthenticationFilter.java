package filters;

import by.kovalenko.entity.UserEntity;
import by.kovalenko.exception.UserNotFoundException;
import by.kovalenko.service.UserService;
import by.kovalenko.util.ServiceContextUtil;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AuthPath;
import model.View;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@WebFilter({
        "/api/login",
        "/api/logout",
})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("AuthenticationFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        switch (AuthPath.of(httpServletRequest.getRequestURI())) {
            case LOGIN:
                login(httpServletRequest, httpServletResponse);
                break;
            case LOGOUT:
                logout(httpServletRequest, httpServletResponse);
                break;
        }

    }

    private void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServiceContextUtil serviceContextUtil = new ServiceContextUtil();
        ApplicationContext serviceContext = serviceContextUtil.getContext();
        UserService userService = (UserService) serviceContext.getBean("userServiceImpl");
        try {
            UserEntity userByUsernameAndPassword = userService.findByUsernameAndPassword(httpServletRequest.getParameter("username"), httpServletRequest.getParameter("password"));
            httpServletRequest.getSession().isNew();
            httpServletRequest.getSession().setAttribute("user", userByUsernameAndPassword);
            httpServletRequest.getSession().setAttribute("ROLE", userByUsernameAndPassword.getRole().name());
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/view/page");
        } catch (IOException | UserNotFoundException e) {
            httpServletRequest.getSession().invalidate();
            httpServletRequest.setAttribute("error", "Not valid username or password");
            httpServletRequest.getRequestDispatcher(View.LOGIN.getViewPath()).forward(httpServletRequest, httpServletResponse);
        }
    }

    private void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getSession().invalidate();
        httpServletRequest.getRequestDispatcher(View.LOGIN.getViewPath()).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {
    }
}
