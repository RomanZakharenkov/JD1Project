package by.rzmarket.dao;

import by.rzmarket.connection.ConnectionPool;
import by.rzmarket.dto.BasketDto;
import by.rzmarket.entity.LineItem;
import by.rzmarket.entity.Product;
import by.rzmarket.entity.Role;
import by.rzmarket.entity.User;
import by.rzmarket.exception.DaoException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LineItemDao {

    private static final LineItemDao INSTANCE = new LineItemDao();
    private static final String GET_PRODUCT = "";
    private static final String GET_ITEMS_BY_ORDER = "" +
            "SELECT product_id, count " +
            "FROM shop.line_item " +
            "WHERE line_item.order_id = ?";

    public List<LineItem> getAllItems(List<LineItem> items) {
        List<LineItem> result = new ArrayList<>();
        for (LineItem item : items) {
            Product product = ProductDao.getInstance().getById(item.getProduct().getId()).get();
            LineItem lineItem = LineItem.builder()
                    .product(product)
                    .count(item.getCount())
                    .build();
            boolean check = true;
            for (int i = 0; i < result.size(); i++) {
                if (lineItem.getProduct().getId().equals(result.get(i).getProduct().getId())) {
                    result.get(i).setCount(result.get(i).getCount() + lineItem.getCount());
                    check = false;
                }
            }
            if (check) {
                result.add(lineItem);
            }
        }
        return result;
    }

    public List<LineItem> getItemsByOrderId(Integer id) {
        List<LineItem> items = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEMS_BY_ORDER)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                items.add(LineItem.builder()
                        .product(ProductDao.getInstance().getById(resultSet.getInt(1)).get())
                        .count(resultSet.getInt(2))
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return items;
    }


    public static LineItemDao getInstance() {
        return INSTANCE;
    }
}
