package by.rzmarket.dao;

import by.rzmarket.connection.ConnectionPool;
import by.rzmarket.entity.Discount;
import by.rzmarket.entity.User;
import by.rzmarket.exception.DaoException;
import by.rzmarket.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscountDao {

    private static final DiscountDao INSTANCE = new DiscountDao();
    private static final String DELETE = "DELETE FROM shop.discount WHERE user_id = ? ;";
    private static final String ADD = "INSERT INTO shop.discount (user_id, precent) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE shop.discount SET precent = ? WHERE user_id = ? ;";
    private static final String GET_BY_USER =
            "SELECT " +
                    "u.first_name AS firstName, " +
                    "u.last_name AS lastName," +
                    "u.email AS email, " +
                    "d.precent AS precent " +
                    "FROM shop.discount d " +
                    "LEFT JOIN shop.\"user\" u " +
                    "ON d.user_id = u.id " +
                    "WHERE d.user_id = ? ;";

    public Optional<Discount> getByUser(User user) {
        Discount discount = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER)) {
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                discount = Discount.builder()
                        .user(User.builder()
                                .firstName(resultSet.getString("firstName"))
                                .lastName(resultSet.getString("lastName"))
                                .email(resultSet.getString("email"))
                                .build())
                        .precent(resultSet.getInt("precent"))
                        .build();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(discount);
    }

    public boolean delete(Discount discount) {
        boolean result;
        try {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setInt(1, discount.getUser().getId());
                result = preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public boolean add(@NonNull Discount discount) {
        boolean result;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD, RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, discount.getUser().getId());
            preparedStatement.setInt(2, discount.getPrecent());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            result = keys.next();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public boolean update(@NonNull Discount discount) {
        boolean result;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, discount.getPrecent());
            preparedStatement.setInt(2, discount.getUser().getId());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            result = keys.next();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public static DiscountDao getInstance() {
        return INSTANCE;
    }
}
