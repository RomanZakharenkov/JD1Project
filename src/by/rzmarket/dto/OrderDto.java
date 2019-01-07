package by.rzmarket.dto;

import by.rzmarket.entity.LineItem;
import by.rzmarket.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Order order;
    private List<LineItem> lineItems;
}
