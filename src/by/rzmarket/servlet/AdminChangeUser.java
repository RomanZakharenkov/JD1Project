package by.rzmarket.servlet;

import by.rzmarket.entity.Role;
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

@WebServlet(value = "/admin-change-user", name = "adminChangeUser")
public class AdminChangeUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("adminChangeUser"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        User user = (User) req.getSession().getAttribute("user");
        if (req.getParameter("password").equals(req.getParameter("password2"))) {
            User newUser = User.builder()
                    .firstName(req.getParameter("firstName"))
                    .lastName(req.getParameter("lastName"))
                    .email(req.getParameter("email"))
                    .number(req.getParameter("number"))
                    .password(req.getParameter("password") == "" ? ((User) req.getSession().getAttribute("user")).getPassword() : req.getParameter("password"))
                    .id(user.getId())
                    .role(Role.getByName(req.getParameter("role")))
                    .build();
            UserService.getInstance().update(newUser);
            resp.sendRedirect("/all-users");
        } else {
            resp.sendRedirect("/admin-change-user");
        }
    }
}
