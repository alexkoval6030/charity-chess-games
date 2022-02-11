package servlet;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.xml.bind.ValidationException;
import service.UserService;
import service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = UserServiceImpl.getInstance();
        UserDao userDao = UserDaoImpl.getInstance();
        if (userDao.findByEmail(email) != null){
            request.setAttribute("error", "User with such email already registered");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        } else {
            try {
                userService.createUser(UUID.randomUUID(), firstname, lastname, email, username, password);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } catch (ValidationException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
            }
        }
    }
}
