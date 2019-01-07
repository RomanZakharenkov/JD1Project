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

@WebServlet("/change-user")
public class ChangeUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("changeUser"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (req.getParameter("password").equals(req.getParameter("password2"))) {
            User user = User.builder()
                    .firstName(req.getParameter("firstName"))
                    .lastName(req.getParameter("lastName"))
                    .email(req.getParameter("email"))
                    .number(req.getParameter("number"))
                    .password(req.getParameter("password"))
                    .id(currentUser.getId())
                    .role(currentUser.getRole())
                    .build();
            UserService.getInstance().update(user);
            resp.sendRedirect("/personal");
        } else {
            resp.sendRedirect("/change-user");
        }


    }
}
