package beyond.samdasoo.admin.repository;

import beyond.samdasoo.admin.dto.TargetSalesStatusDto;
import beyond.samdasoo.common.dto.SearchCond;

public interface TargetSaleRepositoryCustom {
    TargetSalesStatusDto findTargetSalesStatus(SearchCond searchCond);
}
