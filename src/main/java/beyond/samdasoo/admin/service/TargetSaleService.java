package beyond.samdasoo.admin.service;


import beyond.samdasoo.admin.dto.TargetSaleRequestDto;
import beyond.samdasoo.admin.dto.TargetSaleResponseDto;

import java.util.List;

public interface TargetSaleService {

    void addTargetSale(TargetSaleRequestDto request);

    List<TargetSaleResponseDto> getTargetSaleByUserName(String userName, int year);
}
