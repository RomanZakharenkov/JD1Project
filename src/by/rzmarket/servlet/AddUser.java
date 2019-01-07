package by.rzmarket.servlet;

import by.rzmarket.dto.UserDto;
import by.rzmarket.entity.Role;
import by.rzmarket.entity.User;
import by.rzmarket.service.UserService;
import by.rzmarket.util.JspHelper;
import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/registration")
public class AddUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        User user = User.builder()
                .role(Role.USER)
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .number(req.getParameter("number"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();
        UserService.getInstance().add(user);
        resp.sendRedirect("/all-products");
    }
}
