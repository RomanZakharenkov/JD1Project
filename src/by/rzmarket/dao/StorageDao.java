package by.rzmarket.dao;

import by.rzmarket.connection.ConnectionPool;
import by.rzmarket.entity.LineItem;
import by.rzmarket.entity.Product;
import by.rzmarket.entity.Storage;
import by.rzmarket.exception.DaoException;
import by.rzmarket.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StorageDao {

    private static final StorageDao INSTANCE = new StorageDao();
    private static final String IS_PRESENT_PRODUCT = "SELECT \"count\" FROM shop.storage WHERE product_id = ? ;";
    private static final String UPDATE_COUNT_BY_PRODUCT = "UPDATE shop.storage SET \"count\" = ? WHERE product_id = ? ;";
    private static final String ADD_PRODUCT_AND_COUNT = "INSERT INTO shop.storage (product_id, \"count\") VALUES (?, ?)";
    private static final String GET_COUNT_BY_PRODUCT =
            "SELECT " +
                    " \"count\" AS count " +
                    "FROM shop.storage " +
                    "WHERE product_id = ? ;";


    public Integer getCountByProduct(Product product) {
        Integer count = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_BY_PRODUCT)) {
            preparedStatement.setInt(1, product.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return count;
    }

    public boolean isPresentProduct(LineItem lineItem) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(IS_PRESENT_PRODUCT)) {
            preparedStatement.setInt(1, lineItem.getProduct().getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result = resultSet.getInt(1) >= lineItem.getCount();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public boolean isPresent(Integer id) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(IS_PRESENT_PRODUCT)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            result = resultSet.next();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public boolean addCountByProduct(Storage storage) {
        boolean result = false;
        if (isPresent(storage.getProductId())) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNT_BY_PRODUCT, RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, getCountByProduct(Product.builder()
                        .id(storage.getProductId())
                        .build()) + storage.getCount());
                preparedStatement.setInt(2, storage.getProductId());
                preparedStatement.executeUpdate();
                ResultSet keys = preparedStatement.getGeneratedKeys();
                result = keys.next();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        } else {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_AND_COUNT, RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, storage.getProductId());
                preparedStatement.setInt(2, storage.getCount());
                preparedStatement.executeUpdate();
                ResultSet keys = preparedStatement.getGeneratedKeys();
                result = keys.next();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return result;
    }

    public boolean decreaseCountByProduct(Product product, Integer count) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNT_BY_PRODUCT)) {
            preparedStatement.setInt(1, getCountByProduct(product) - count);
            preparedStatement.setInt(2, product.getId());
            Integer value = preparedStatement.executeUpdate();
            result = value != null;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public static StorageDao getInstance() {
        return INSTANCE;
    }
}
