package by.rzmarket.servlet;

import by.rzmarket.entity.User;
import by.rzmarket.service.UserService;
import by.rzmarket.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@WebServlet(value = "/all-users", name = "allUsers")
public class GetAllUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("users", UserService.getInstance().getAll());
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("all-users"))
                .forward(req, resp);
    }

}
