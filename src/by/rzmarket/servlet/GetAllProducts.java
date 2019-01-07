package by.rzmarket.servlet;

import by.rzmarket.dto.FilterDto;
import by.rzmarket.entity.LineItem;
import by.rzmarket.entity.Product;
import by.rzmarket.service.ProductService;
import by.rzmarket.util.FileUtils;
import by.rzmarket.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/all-products")
public class GetAllProducts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer minPrice = null;
        Integer maxPrice = null;
        FilterDto filterDto = (FilterDto) req.getSession().getAttribute("filter");
        if (filterDto == null) {
            filterDto = FilterDto.builder()
                    .tv("")
                    .audio("")
                    .minPrice("")
                    .maxPrice("")
                    .sort("desc")
                    .build();
            req.getSession().setAttribute("filter", filterDto);
        }
        if (!"".equals(filterDto.getMinPrice())) {
            minPrice = Integer.parseInt(filterDto.getMinPrice());
        }
        if (!"".equals(filterDto.getMaxPrice())) {
            maxPrice = Integer.parseInt(filterDto.getMaxPrice());
        }

        List<Product> products = ProductService.getInstance().getAll(filterDto.getTv(), filterDto.getAudio(), minPrice, maxPrice, filterDto.getSort());
        FileUtils.createFile(products);
        req.setAttribute("products", ProductService.getInstance().getAll(filterDto.getTv(), filterDto.getAudio(), minPrice, maxPrice, filterDto.getSort()));
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("all-products"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("basket".equals(req.getParameter("form"))) {
            LineItem item = LineItem.builder()
                    .product(Product.builder()
                            .id(Integer.parseInt(req.getParameter("productId")))
                            .build())
                    .count(1)
                    .build();
            List<LineItem> items = (List<LineItem>) req.getSession().getAttribute("items");

            items.add(item);
            req.getSession().setAttribute("items", items);
            req.getSession().setAttribute("count", items.size());
        } else {
            FilterDto filterDto = FilterDto.builder()
                    .tv(req.getParameter("tv"))
                    .audio(req.getParameter("audio"))
                    .minPrice(req.getParameter("minPrice"))
                    .maxPrice(req.getParameter("maxPrice"))
                    .sort(req.getParameter("rad"))
                    .build();
            req.getSession().setAttribute("filter", filterDto);
        }
        resp.sendRedirect("/all-products");
    }
}
