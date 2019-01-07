package by.rzmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Storage {

    private Integer productId;
    private Integer count;
}
