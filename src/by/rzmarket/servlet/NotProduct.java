package by.rzmarket.servlet;

import by.rzmarket.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/notproduct")
public class NotProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("notProduct", "error");
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("basket"))
                .forward(req, resp);
    }
}
