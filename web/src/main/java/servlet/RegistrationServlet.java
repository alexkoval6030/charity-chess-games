package servlet;


import by.kovalenko.dao.UserDao;
import by.kovalenko.exception.ValidationException;
import by.kovalenko.service.UserService;
import by.kovalenko.util.DaoContextUtil;
import by.kovalenko.util.ServiceContextUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.View;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.UUID;

@WebServlet({
        "/form/registration",
        "/api/registration"
})
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(View.REGISTRATION.getViewPath()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoContextUtil daoContextUtil = new DaoContextUtil();
        ApplicationContext daoContext = daoContextUtil.getContext();
        UserDao userDao = (UserDao) daoContext.getBean("userDaoImpl");
        ServiceContextUtil serviceContextUtil = new ServiceContextUtil();
        ApplicationContext serviceContext = serviceContextUtil.getContext();
        UserService userService = (UserService) serviceContext.getBean("userServiceImpl");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userDao.findByEmail(email) != null){
            request.setAttribute("error", "User with such email already registered");
            request.getRequestDispatcher(View.REGISTRATION.getViewPath()).forward(request, response);
        } else {
            try {
                userService.createUser(UUID.randomUUID(), firstname, lastname, email, username, password);
                request.getRequestDispatcher(View.LOGIN.getViewPath()).forward(request, response);
            } catch (ValidationException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher(View.REGISTRATION.getViewPath()).forward(request, response);
            }
        }
    }
}
