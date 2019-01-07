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

@WebServlet(value = "/finished-orders", name = "finishedOrder")
public class FinishedOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("finishedOrders", OrderService.getInstance().getOrders(Status.FINISHED));
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("finished-orders"))
                .forward(req, resp);
    }
}
