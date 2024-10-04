package beyond.samdasoo.admin.dto;

import beyond.samdasoo.admin.entity.TargetSale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetSaleResponseDto {
    private Long ProdNo;

    private int month;

    private int monthTarget;

    private int year;

    public TargetSaleResponseDto(TargetSale targetSale) {
        this.ProdNo = targetSale.getTargetSaleNo();
        this.month = targetSale.getMonth();
        this.monthTarget = targetSale.getMonthTarget();
        this.year = targetSale.getYear();
    }
}
