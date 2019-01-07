package by.rzmarket.servlet;

import by.rzmarket.dto.FilterDto;
import by.rzmarket.dto.LoginUserDto;
import by.rzmarket.entity.LineItem;
import by.rzmarket.entity.User;
import by.rzmarket.service.UserService;
import by.rzmarket.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("currentUser");
        System.out.println(user);
        if (user == null) {
            getServletContext()
                    .getRequestDispatcher(JspHelper.getPath("login"))
                    .forward(req, resp);
        } else {
            resp.sendRedirect("/logout");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = UserService.getInstance().login(new LoginUserDto(login, password));
        List<LineItem> items = new ArrayList<>();

        if (user != null) {
            req.getSession().setAttribute("currentUser", user);
            req.getSession().setAttribute("items", items);
            req.getSession().setAttribute("count", items.size());
            req.getSession().setAttribute("filter", FilterDto.builder()
                    .tv("")
                    .audio("")
                    .minPrice("")
                    .maxPrice("")
                    .sort("desc")
                    .build());
            req.getSession().setAttribute("errorLogin", "");
            resp.sendRedirect("/all-products");
        } else {
            req.getSession().setAttribute("errorLogin", "e");
            resp.sendRedirect("/login?login=" + (login != null ? login : ""));
        }
    }
}
