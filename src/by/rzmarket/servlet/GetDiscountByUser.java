package by.rzmarket.servlet;

import by.rzmarket.entity.User;
import by.rzmarket.service.DiscountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/getdiscount")
public class GetDiscountByUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = User.builder()
                .id(1)
                .build();
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_16.name());
        resp.getWriter().write(DiscountService.getINSTANCE().getByUser(user).toString());
    }
}
