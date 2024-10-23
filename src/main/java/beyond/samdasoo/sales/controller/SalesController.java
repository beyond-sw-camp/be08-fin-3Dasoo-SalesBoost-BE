package beyond.samdasoo.sales.controller;

import beyond.samdasoo.common.dto.SearchCond;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.sales.dto.SalesRequestDto;
import beyond.samdasoo.sales.dto.SalesResponseDto;
import beyond.samdasoo.sales.dto.SalesStatusDto;
import beyond.samdasoo.sales.service.SalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sales APIs", description = "매출 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/sales")
public class SalesController {

    private final SalesService salesService;

    // 매출 전체 조회
    @GetMapping
    @Operation(summary = "매출 전체 조회", description = "등록된 전체 매출 조회")
    public BaseResponse<List<SalesResponseDto>> getSales() {
        List<SalesResponseDto> sales = salesService.getAllSales();
        return new BaseResponse<>(sales);
    }

    // 특정 매출 조회
    @GetMapping("/{no}")
    @Operation(summary = "매출 단건 조회", description = "등록된 매출중 매출 번호로 단건 조회")
    public BaseResponse<SalesResponseDto> getSales(@PathVariable("no") Long no) {
        SalesResponseDto sales = salesService.getSales(no);
        return new BaseResponse<>(sales);
    }

    // 매출 생성
    @PostMapping
    @Operation(summary = "매출 생성", description = "신규 매출 등록")
    public BaseResponse<SalesResponseDto> createSales(@RequestBody SalesRequestDto salesRequestDto) {
        SalesResponseDto sales = salesService.createSales(salesRequestDto);
        return new BaseResponse<>(sales);
    }

    // 매출 삭제
    @DeleteMapping("/{no}")
    @Operation(summary = "매출 삭제", description = "기존 계약 삭제")
    public BaseResponse<String> deleteSales(@PathVariable("no") Long no) {
        salesService.deleteSales(no);
        return new BaseResponse<>("매출 번호 " + no + " 삭제 완료");
    }

    // 매출 수정
    @PatchMapping("/{no}")
    @Operation(summary = "매출 수정", description = "기존 계약 수정")
    public BaseResponse<SalesResponseDto> updateSales(@PathVariable("no") Long no, @RequestBody SalesRequestDto salesRequestDto) {
        SalesResponseDto sales = salesService.updateSales(no, salesRequestDto);

        return new BaseResponse<>(sales);
    }

    @PostMapping("/status/main")
    public BaseResponse<SalesStatusDto> getSalesStatus(@RequestBody SearchCond searchCond) {
        SalesStatusDto salesStatus = salesService.getSalesStatus(searchCond);
        return new BaseResponse<>(salesStatus);
    }

}
