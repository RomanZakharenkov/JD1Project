package by.rzmarket.servlet;

import by.rzmarket.entity.Status;
import by.rzmarket.service.OrderService;
import by.rzmarket.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/reserved-orders", name = "reservedOrder")
public class ReservedOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("reservedOrders", OrderService.getInstance().getOrders(Status.RESERVED));
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("reserved-orders"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer orderId = Integer.parseInt(req.getParameter("orderId"));
        OrderService.getInstance().updateOrder(orderId, Status.FINISHED);
        resp.sendRedirect("/reserved-orders");
    }
}
