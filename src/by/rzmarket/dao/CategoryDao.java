package by.rzmarket.dao;

import by.rzmarket.connection.ConnectionPool;
import by.rzmarket.entity.Category;
import by.rzmarket.exception.DaoException;
import by.rzmarket.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryDao {

    private static final CategoryDao INSTANCE = new CategoryDao();
    private static final String ADD = "INSERT INTO shop.category (name) VALUES (?);";
    private static final String UPDATE = "UPDATE shop.category SET name = ? WHERE id = ? ;";

    public Integer add(@NonNull Category category) {
        Integer id = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                id = keys.getInt(1);
                category.setId(id);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return id;
    }

    public boolean update(@NonNull Category category) {
        boolean result;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            result = keys.next();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public static CategoryDao getInstance() {
        return INSTANCE;
    }
}
