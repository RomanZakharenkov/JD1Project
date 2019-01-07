package by.rzmarket.servlet;

import by.rzmarket.dao.StorageDao;
import by.rzmarket.entity.Storage;
import by.rzmarket.service.ProductService;
import by.rzmarket.service.StorageService;
import by.rzmarket.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add-count-product", name = "addCountProduct")
public class AddCountProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allNomen", ProductService.getInstance().getAllNomen());
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("add-count-product"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Storage storage = Storage.builder()
                .productId(Integer.parseInt(req.getParameter("product-nomen")))
                .count(Integer.parseInt(req.getParameter("count")))
                .build();
        boolean check = false;
        check = StorageService.getInstance().addCountProduct(storage);
        resp.sendRedirect("add-count-product?check=" + check);
    }
}
