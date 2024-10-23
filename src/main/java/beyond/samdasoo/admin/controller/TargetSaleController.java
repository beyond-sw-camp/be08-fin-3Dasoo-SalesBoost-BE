package beyond.samdasoo.admin.controller;

import beyond.samdasoo.admin.dto.TargetSaleRequestDto;
import beyond.samdasoo.admin.dto.TargetSaleResponseDto;
import beyond.samdasoo.admin.dto.TargetSalesStatusDto;
import beyond.samdasoo.admin.service.TargetSaleService;
import beyond.samdasoo.common.dto.SearchCond;
import beyond.samdasoo.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/targetsales")
@RequiredArgsConstructor
@Tag(name = "TargetSale APIs", description = "목표 매출 관련 API")
public class TargetSaleController {

    private final TargetSaleService targetSaleService;

    @PostMapping
    @Operation(summary = "목표 매출 금액 생성", description = "관리자 계정에서 해당 영업사원의 특정 상품에 대한 목표 매출을 설정")
    public BaseResponse<String> addTargetSale(@RequestBody TargetSaleRequestDto request) {

        targetSaleService.addTargetSale(request);

        return new BaseResponse<>("목표 매출 금액 설정을 완료했습니다.");
    }

    @GetMapping("/{userName}")
    @Operation(summary = "목표 매출 금액 조회", description = "특정 영업사원의 특정 년도 목표 매출 금액 조회")
    public BaseResponse<List<TargetSaleResponseDto>> getTargetSaleByUserName(@PathVariable("userName") String userName, @RequestParam("year") int year) {

        List<TargetSaleResponseDto> response = targetSaleService.getTargetSaleByUserName(userName, year);

        return new BaseResponse<>(response);
    }

    @PostMapping("/status/main")
    public BaseResponse<TargetSalesStatusDto> getSalesStatus(@RequestBody SearchCond searchCond) {
        TargetSalesStatusDto targetSalesStatus = targetSaleService.getTargetSalesStatus(searchCond);
        return new BaseResponse<>(targetSalesStatus);
    }
}
