package by.rzmarket.service;

import by.rzmarket.dao.DiscountDao;
import by.rzmarket.entity.Discount;
import by.rzmarket.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscountService {

    private static final DiscountService INSTANCE = new DiscountService();

    public Optional<Discount> getByUser(User user) {
        return DiscountDao.getInstance().getByUser(user);
    }

    public boolean delete(Discount discount) {
        return DiscountDao.getInstance().delete(discount);
    }

    public boolean add(Discount discount) {
        return DiscountDao.getInstance().add(discount);
    }

    public boolean update(Discount discount) {
        return DiscountDao.getInstance().update(discount);
    }

    public static DiscountService getINSTANCE() {
        return INSTANCE;
    }
}
