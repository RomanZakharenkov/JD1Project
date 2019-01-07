package by.rzmarket.servlet;

import by.rzmarket.entity.LineItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/clearbasket")
public class ClearBasket extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<LineItem> items = new ArrayList<>();
        req.getSession().setAttribute("items", items);
        req.getSession().setAttribute("count", items.size());
        resp.sendRedirect("/basket");
    }
}
