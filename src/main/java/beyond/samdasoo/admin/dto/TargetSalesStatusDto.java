package beyond.samdasoo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TargetSalesStatusDto {
    private long monthTargetSales;
    private long yearTargetSales;
}
