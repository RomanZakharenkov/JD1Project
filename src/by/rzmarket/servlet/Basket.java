package by.rzmarket.servlet;

import by.rzmarket.entity.LineItem;
import by.rzmarket.entity.Order;
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
import java.util.Map;

@WebServlet("/basket")
public class Basket extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<LineItem> items = (List<LineItem>) req.getSession().getAttribute("items");
        List<LineItem> order = OrderService.getInstance().createOrder(items);
        Integer summ = 0;
        for (LineItem item : order) {
            summ += item.getProduct().getPrice() * item.getCount();
        }
        req.getSession().setAttribute("order", order);
        req.getSession().setAttribute("summ", summ);
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("basket"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<LineItem> lineItems = (List<LineItem>) req.getSession().getAttribute("order");
        User user = (User) req.getSession().getAttribute("currentUser");
        if (lineItems.size() != 0) {
            if (OrderService.getInstance().saveOrder(lineItems, user) == -1) {
                System.out.println("-1-1-1-1-1-1-1");
                resp.sendRedirect("/notproduct");
            } else {
                List<LineItem> items = new ArrayList<>();
                req.getSession().setAttribute("items", items);
                req.getSession().setAttribute("count", items.size());
                resp.sendRedirect("/personal");
            }
        } else {
            resp.sendRedirect("/personal");
        }
    }
}
