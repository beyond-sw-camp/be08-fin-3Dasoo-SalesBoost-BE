package beyond.samdasoo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {
    private String prodCode;

    private String name;

    private String engName;

    private String abbrName;

    private String uppGroup;

    private LocalDate releaseDate;

    private String dept;

    private int quantity;

    private String unit;

    private String field;

    private int supplyPrice;

    private int taxRate;

    private int price;
}
