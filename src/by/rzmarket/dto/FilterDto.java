package by.rzmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterDto {

    private String tv;
    private String audio;
    private String minPrice;
    private String maxPrice;
    private String sort;
}
