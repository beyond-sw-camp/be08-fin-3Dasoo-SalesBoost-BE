package beyond.samdasoo.admin.service;


import beyond.samdasoo.admin.dto.TargetSaleRequestDto;
import beyond.samdasoo.admin.dto.TargetSaleResponseDto;
import beyond.samdasoo.admin.dto.TargetSalesStatusDto;
import beyond.samdasoo.common.dto.SearchCond;
import beyond.samdasoo.sales.dto.SalesStatusDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TargetSaleService {

    void addTargetSale(TargetSaleRequestDto request);

    List<TargetSaleResponseDto> getTargetSaleByUserName(String userName, int year);

    TargetSalesStatusDto getTargetSalesStatus(SearchCond searchCond);
}
