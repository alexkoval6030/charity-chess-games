package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.View;

import java.io.IOException;

@WebServlet({
        "/form/login"
})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(View.LOGIN.getViewPath()).forward(request, response);
    }

}
