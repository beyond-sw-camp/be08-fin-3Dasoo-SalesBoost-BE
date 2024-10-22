package beyond.samdasoo.sales.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesPredictionDto {
    private double predictedPrice;
    private String predictedTime;
    private double predictGrowRate;
}
