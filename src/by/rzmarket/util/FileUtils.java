package by.rzmarket.util;

import by.rzmarket.entity.Product;
import by.rzmarket.exception.DaoException;
import lombok.experimental.UtilityClass;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@UtilityClass
public class FileUtils {

    public void createFile(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\IT-academy\\Homework\\shop\\web\\report\\PriceList.txt", true))) {
            for (Product product : products) {
                writer.append(product.getBrand());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
}
