package by.rzmarket.servlet;

import by.rzmarket.entity.Product;
import by.rzmarket.service.ProductService;
import by.rzmarket.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/productInfo")
public class ProductInfo extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer productId = Integer.parseInt(req.getParameter("id"));
        Product product = ProductService.getInstance().getById(productId);
        req.setAttribute("product", product);
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("productInfo"))
                .forward(req, resp);

    }
}
