package by.rzmarket.servlet;

import by.rzmarket.dto.FilterDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/clearfilter")
public class ClearFilter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("filter", FilterDto.builder()
                .tv("")
                .audio("")
                .minPrice("")
                .maxPrice("")
                .sort("desc")
                .build());
        resp.sendRedirect("/all-products");
    }
}
