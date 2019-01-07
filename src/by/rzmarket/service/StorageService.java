package by.rzmarket.service;

import by.rzmarket.dao.StorageDao;
import by.rzmarket.entity.Storage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StorageService {

    private static final StorageService INSTANCE = new StorageService();

    public boolean addCountProduct(Storage storage) {
        return StorageDao.getInstance().addCountByProduct(storage);
    }

    public static StorageService getInstance() {
        return INSTANCE;
    }
}
