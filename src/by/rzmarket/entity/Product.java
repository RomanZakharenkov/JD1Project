package by.rzmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Integer id;
    private String brand;
    private String model;
    private Integer price;
    private Category category;
    private String description;
    private String image;
}
