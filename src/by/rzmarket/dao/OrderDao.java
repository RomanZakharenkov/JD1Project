package by.rzmarket.dao;

import by.rzmarket.connection.ConnectionPool;
import by.rzmarket.dto.OrderDto;
import by.rzmarket.entity.Category;
import by.rzmarket.entity.LineItem;
import by.rzmarket.entity.Order;
import by.rzmarket.entity.Product;
import by.rzmarket.entity.Status;
import by.rzmarket.entity.User;
import by.rzmarket.exception.DaoException;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDao {

    private static final OrderDao INSTANCE = new OrderDao();
    private static final String SAVE_LINE_ITEM = "" +
            "INSERT INTO shop.line_item (order_id, product_id, \"count\") " +
            "VALUES (?, ?, ?)";
    private static final String SAVE_ORDER = "" +
            "INSERT INTO shop.\"order\" (user_id, status, \"date\") " +
            "VALUES (?, ?, ?)";
    private static final String GET_ORDER = "";
    private static final String GET_ALL = "" +
            "SELECT \"order\".id, \"order\".\"date\", \"order\".status " +
            "FROM shop.\"order\"" +
            "WHERE \"order\".user_id = ? ";
    private static final String GET = "" +
            "SELECT \"order\".id, \"order\".\"date\", \"order\".status " +
            "FROM shop.\"order\" " +
            "WHERE \"order\".status = ? " +
            "ORDER BY \"date\"";
    private static final String UPDATE = "" +
            "UPDATE shop.\"order\" " +
            "SET status = ? " +
            "WHERE id = ?";

    public boolean updateOrder(Integer orderId, Status status) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, status.getName());
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();

            while (keys.next()) {
                result = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public Integer saveOrder(List<LineItem> lineItems, User user) {
        Integer result = null;
        boolean isPresent = true;
        for (LineItem lineItem : lineItems) {
            if (!StorageDao.getInstance().isPresentProduct(lineItem)) {
                isPresent = false;
            }
        }
        if (isPresent) {
            for (LineItem lineItem : lineItems) {
                Order order = null;
                if (result == null) {
                    try (Connection connection = ConnectionPool.getConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ORDER, RETURN_GENERATED_KEYS)) {
                        preparedStatement.setInt(1, user.getId());
                        preparedStatement.setString(2, Status.PROCESSED.getName());
                        preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
                        preparedStatement.executeUpdate();

                        ResultSet resultSet = preparedStatement.getGeneratedKeys();
                        while (resultSet.next()) {
                            result = resultSet.getInt(1);
                            order = Order.builder()
                                    .id(result)
                                    .build();
                            lineItem.setOrder(order);
                        }
                    } catch (SQLException e) {
                        throw new DaoException(e);
                    }
                }
                try (Connection connection = ConnectionPool.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(SAVE_LINE_ITEM)) {
                    preparedStatement.setInt(1, result);
                    preparedStatement.setInt(2, lineItem.getProduct().getId());
                    preparedStatement.setInt(3, lineItem.getCount());
                    preparedStatement.executeUpdate();
                    StorageDao.getInstance().decreaseCountByProduct(lineItem.getProduct(), lineItem.getCount());
                } catch (SQLException e) {
                    throw new DaoException(e);
                }

            }
        } else {
            result = -1;
        }
        return result;
    }

    public List<OrderDto> getOrders(Status status){
        List<OrderDto> list = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET)) {
            preparedStatement.setString(1, status.getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(OrderDto.builder()
                        .order(Order.builder()
                                .id(resultSet.getInt(1))
                                .date(resultSet.getDate(2).toLocalDate())
                                .status(Status.getByName(resultSet.getString(3)))
                                .build())
                        .lineItems(LineItemDao.getInstance().getItemsByOrderId(resultSet.getInt(1)))
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }


    public Optional<Order> getOrder(List<LineItem> items) {
        Order order = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER)) {
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return Optional.ofNullable(order);
    }

    public List<OrderDto> getAllOrdersByUserId(Integer id) {
        List<OrderDto> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orders.add(OrderDto.builder()
                        .order(Order.builder()
                                .id(resultSet.getInt(1))
                                .date(resultSet.getDate(2).toLocalDate())
                                .status(Status.getByName(resultSet.getString(3)))
                                .build())
                        .lineItems(LineItemDao.getInstance().getItemsByOrderId(resultSet.getInt(1)))
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }
}
