package by.rzmarket.service;

import by.rzmarket.dao.LineItemDao;
import by.rzmarket.dao.OrderDao;
import by.rzmarket.dto.OrderDto;
import by.rzmarket.entity.LineItem;
import by.rzmarket.entity.Order;
import by.rzmarket.entity.Status;
import by.rzmarket.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderService {

    private static final OrderService INSTANCE = new OrderService();

    public List<LineItem> createOrder(List<LineItem> items) {
        return LineItemDao.getInstance().getAllItems(items);
    }

    public Integer saveOrder(List<LineItem> items, User user){
        return OrderDao.getInstance().saveOrder(items, user);
    }

    public List<OrderDto> getOrders(Status status) {
        return OrderDao.getInstance().getOrders(status);
    }

    public boolean updateOrder(Integer orderId, Status status) {
        return OrderDao.getInstance().updateOrder(orderId, status);
    }

    public List<OrderDto> getAllOrdersByUserId(Integer id) {
        return OrderDao.getInstance().getAllOrdersByUserId(id);
    }

//    public List<LineItem> getOrder(List<LineItem> items) {
//        List<LineItem> resultItems = new ArrayList<>();
//
//        return resultItems;
//    }

    public static OrderService getInstance() {
        return INSTANCE;
    }
}
