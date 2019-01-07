package by.rzmarket.servlet;

import by.rzmarket.dto.OrderDto;
import by.rzmarket.entity.User;
import by.rzmarket.service.OrderService;
import by.rzmarket.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/personal")
public class Personal extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("currentUser");
        req.setAttribute("orders", OrderService.getInstance().getAllOrdersByUserId(user.getId()));
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("personal"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/change-user");
    }
}
