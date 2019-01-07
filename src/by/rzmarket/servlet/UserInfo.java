package by.rzmarket.servlet;

import by.rzmarket.dto.ShortInfoUserDto;
import by.rzmarket.entity.User;
import by.rzmarket.service.OrderService;
import by.rzmarket.service.UserService;
import by.rzmarket.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-info")
public class UserInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("id"));
        User user = UserService.getInstance().getById(userId);
        req.setAttribute("orders", OrderService.getInstance().getAllOrdersByUserId(user.getId()));
        System.out.println(user);
        req.getSession().setAttribute("user", user);
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("userInfo"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/admin-change-user");
    }
}
