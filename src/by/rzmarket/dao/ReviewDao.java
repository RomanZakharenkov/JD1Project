package by.rzmarket.dao;

import by.rzmarket.connection.ConnectionPool;
import by.rzmarket.entity.Product;
import by.rzmarket.entity.Review;
import by.rzmarket.exception.DaoException;
import by.rzmarket.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDao {

    private static final ReviewDao INSTANCE = new ReviewDao();
    private static final String DELETE = "DELETE FROM shop.review WHERE id = ? ;";
    private static final String ADD = "INSERT INTO shop.review (product_id, \"date\", text) VALUES (?, ?, ?)";
    private static final String GET_ALL_BY_PRODUCT =
            "SELECT " +
                    "r.\"date\" AS dateReview, " +
                    "r.text AS text, " +
                    "p.brand AS brand, " +
                    "p.model AS model " +
                    "FROM shop.review r " +
                    "LEFT JOIN shop.product p " +
                    "ON r.product_id = p.id " +
                    "WHERE r.product_id = ? ;";

    public List<Review> getAllByProduct(Product product) {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BY_PRODUCT)) {
            preparedStatement.setInt(1, product.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                reviews.add(Review.builder()
                        .product(Product.builder()
                                .brand(resultSet.getString("brand"))
                                .model(resultSet.getString("model"))
                                .build())
                        .date(resultSet.getDate("dateReview").toLocalDate())
                        .text(resultSet.getString("text"))
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return reviews;
    }

    public boolean delete(Review review) {
        boolean result;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, review.getId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public Integer add(@NonNull Review review) {
        Integer id = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD, RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, review.getProduct().getId());
            preparedStatement.setDate(2, Date.valueOf(review.getDate()));
            preparedStatement.setString(3, review.getText());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                id = keys.getInt(1);
                review.setId(id);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return id;
    }

    public static ReviewDao getInstance() {
        return INSTANCE;
    }
}
