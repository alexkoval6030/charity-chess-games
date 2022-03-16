package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.View;

import java.io.IOException;

@WebServlet({
        "/api/page",
        "/form/page",
        "/view/page/*",
        "/view/page/",
})
public class PageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(View.START_PAGE.getViewPath()).forward(request, response);
    }
}
