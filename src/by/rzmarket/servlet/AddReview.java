package by.rzmarket.servlet;

import by.rzmarket.entity.Product;
import by.rzmarket.entity.Review;
import by.rzmarket.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@WebServlet("/addreview")
public class AddReview extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Review review = Review.builder()
                .product(Product.builder()
                        .id(1)
                        .build())
//                .date(new Date())
                .text("NEW review")
                .build();
        LocalDate localDate = LocalDate.now();

//        Date date = new Date();
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String text = formatter.format(date);
//        review.setDate();
        Integer value = ReviewService.getINSTANCE().add(review);
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_16.name());
        resp.getWriter().print("Отзыв добавлен. ID отзыва - " + value);
    }
}
