package by.rzmarket.service;

import by.rzmarket.dao.ReviewDao;
import by.rzmarket.entity.Product;
import by.rzmarket.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewService {

    private static final ReviewService INSTANCE = new ReviewService();

    public List<Review> getAllByProduct(Product product) {
        return ReviewDao.getInstance().getAllByProduct(product);
    }

    public boolean delete(Review review) {
        return ReviewDao.getInstance().delete(review);
    }

    public Integer add(Review review) {
        return ReviewDao.getInstance().add(review);
    }

    public static ReviewService getINSTANCE() {
        return INSTANCE;
    }
}
