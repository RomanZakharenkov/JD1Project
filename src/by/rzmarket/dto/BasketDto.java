package by.rzmarket.dto;

import by.rzmarket.entity.LineItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {

    private LineItem lineItem;
    private Integer count;
}
