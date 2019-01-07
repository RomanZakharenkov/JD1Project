package by.rzmarket.dao;

import by.rzmarket.connection.ConnectionPool;
import by.rzmarket.dto.NomenDto;
import by.rzmarket.entity.Category;
import by.rzmarket.entity.Product;
import by.rzmarket.exception.DaoException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDao {

    private static final ProductDao INSTANCE = new ProductDao();
    private static final String UPDATE = "UPDATE shop.product SET brand = ? , model = ? , price = ? , description = ? , image = ? , category_id = ? WHERE id = ? ;";
    private static final String ADD = "INSERT INTO shop.product (brand, model, price, category_id, description, image) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_BY_ID =
            "SELECT " +
                    "p.id AS productId, " +
                    "p.brand AS brand, " +
                    "p.model AS model, " +
                    "p.price AS price, " +
                    "p.description AS description, " +
                    "p.image AS  image, " +
                    "c.id AS categoryId, " +
                    "c.name AS categoryName " +
                    "FROM shop.product p " +
                    "LEFT JOIN shop.category c " +
                    "ON p.category_id = c.id " +
                    "WHERE p.id = ? ;";
    private static final String GET = "" +
            "SELECT p.id, p.brand, p.model, p.price, c.id, c.name, p.description, p.image, storage.\"count\" " +
            "FROM shop.product p " +
            "LEFT JOIN shop.category c " +
            "ON p.category_id = c.id " +
            "LEFT JOIN shop.storage " +
            "ON p.id = storage.product_id " +
            "WHERE \"count\" > 0";
    private static final String GET_ALL_NOMEN = "" +
            "SELECT " +
            "id, brand, model " +
            "FROM shop.product";

    public List<NomenDto> getAllNomen() {
        List<NomenDto> list = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_NOMEN)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(NomenDto.builder()
                        .id(resultSet.getInt(1))
                        .brand(resultSet.getString(2))
                        .model(resultSet.getString(3))
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return list;
    }

    public Optional<Product> getById(Integer id) {
        Product product = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                product = Product.builder()
                        .id(resultSet.getInt("productId"))
                        .brand(resultSet.getString("brand"))
                        .model(resultSet.getString("model"))
                        .price(resultSet.getInt("price"))
                        .description(resultSet.getString("description"))
                        .image(resultSet.getString("image"))
                        .category(Category.builder()
                                .id(resultSet.getInt("categoryId"))
                                .name(resultSet.getString("categoryName"))
                                .build())
                        .build();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(product);
    }

    public Integer add(Product product) {
        Integer id = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getBrand());
            preparedStatement.setString(2, product.getModel());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getCategory().getId());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getImage());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                id = keys.getInt(1);
                product.setId(id);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return id;
    }

    public List<Product> getAll(String tv, String audio, Integer minPrice, Integer maxPrice, String sort) {
        List<Product> products = new ArrayList<>();
        String result = GET;
        boolean isTV = false;
        if ("1".equals(tv)) {
            result += " AND c.id = " + tv;
            isTV = true;
        }
        if ("2".equals(audio)) {
            if (isTV) {
                result = GET + " AND (c.id = " + tv + " OR c.id = " + audio + ") ";
            } else {
                result += " AND c.id = " + audio;
            }
        }
        if (minPrice != null) {
            result += " AND p.price >= " + minPrice;
        }

        if (maxPrice != null) {
            result += " AND p.price <= " + maxPrice;
        }
        result += " ORDER BY p.price " + sort;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(result)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(Product.builder()
                        .id(resultSet.getInt(1))
                        .brand(resultSet.getString(2))
                        .model(resultSet.getString(3))
                        .price(resultSet.getInt(4))
                        .category(Category.builder()
                                .id(resultSet.getInt(5))
                                .name(resultSet.getString(6))
                                .build())
                        .description(resultSet.getString(7))
                        .image(resultSet.getString(8))
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return products;
    }

    public boolean update(@NonNull Product product) {
        boolean result;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getBrand());
            preparedStatement.setString(2, product.getModel());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getCategory().getId());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getImage());
            preparedStatement.setInt(7, product.getId());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            result = keys.next();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public static ProductDao getInstance() {
        return INSTANCE;
    }
}
