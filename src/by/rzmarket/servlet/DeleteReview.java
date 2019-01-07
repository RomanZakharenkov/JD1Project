package by.rzmarket.servlet;

import by.rzmarket.dao.ReviewDao;
import by.rzmarket.entity.Review;
import by.rzmarket.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/deletereview")
public class DeleteReview extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Review review = Review.builder()
                .id(1)
                .build();
        ReviewService.getINSTANCE().delete(review);
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_16.name());
        resp.getWriter().print("Отзыв - " + review.toString() + " удален!");
    }
}
