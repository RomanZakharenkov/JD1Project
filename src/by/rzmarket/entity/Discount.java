package by.rzmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discount {

    private User user;
    private Integer precent;
}
