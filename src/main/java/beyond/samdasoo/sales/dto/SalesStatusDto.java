package beyond.samdasoo.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesStatusDto {
    private long monthSales;
    private long yearSales;
}
