package by.rzmarket.servlet;

import by.rzmarket.entity.Product;
import by.rzmarket.entity.Review;
import by.rzmarket.entity.User;
import by.rzmarket.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@WebServlet("/getreview")
public class GetAllReviewsByProduct extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = Product.builder()
                .id(3)
                .build();
        String value = ReviewService.getINSTANCE().getAllByProduct(product).stream()
                .map(this::toStringPresentation)
                .collect(Collectors.joining());
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_16.name());
        resp.getWriter().print(value);
    }

    private String toStringPresentation(Review review) {
        return review.toString();
//        return "<p>" + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail() + " " + user.getNumber() + "</p>";
    }
}
