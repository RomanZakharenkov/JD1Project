package by.rzmarket.service;

import by.rzmarket.dao.ProductDao;
import by.rzmarket.dto.NomenDto;
import by.rzmarket.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductService {

    private static final ProductService INSTANCE = new ProductService();

    public List<Product> getAll(String tv, String audio, Integer minPrice, Integer maxPrice, String sort) {
        return ProductDao.getInstance().getAll(tv, audio, minPrice, maxPrice, sort);
    }

    public List<NomenDto> getAllNomen() {
        return ProductDao.getInstance().getAllNomen();
    }

    public Product getById(Integer id) {
        return ProductDao.getInstance().getById(id).get();
    }

    public static ProductService getInstance() {
        return INSTANCE;
    }
}

