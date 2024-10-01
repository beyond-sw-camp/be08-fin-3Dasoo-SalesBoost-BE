package beyond.samdasoo.sales.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.sales.dto.SalesRequestDto;
import beyond.samdasoo.sales.dto.SalesResponseDto;
import beyond.samdasoo.sales.service.SalesService;
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
    public BaseResponse<List<SalesResponseDto>> getSales() {
        List<SalesResponseDto> sales = salesService.getAllSales();
        return new BaseResponse<>(sales);
    }

    // 특정 매출 조회
    @GetMapping("/{no}")
    public BaseResponse<SalesResponseDto> getSales(@PathVariable("no") Long no) {
        SalesResponseDto sales = salesService.getSales(no);
        return new BaseResponse<>(sales);
    }

    // 매출 생성
    @PostMapping
    public BaseResponse<SalesResponseDto> createSales(@RequestBody SalesRequestDto salesRequestDto) {
        SalesResponseDto sales = salesService.createSales(salesRequestDto);
        return new BaseResponse<>(sales);
    }

    // 매출 삭제
    @DeleteMapping("/{no}")
    public BaseResponse<String> deleteSales(@PathVariable("no") Long no) {
        salesService.deleteSales(no);
        return new BaseResponse<>("매출 번호 " + no + " 삭제 완료");
    }

    // 매출 수정
    @PatchMapping("/{no}")
    public BaseResponse<String> updateSales(@PathVariable("no") Long no, @RequestBody SalesRequestDto salesRequestDto) {
        salesService.updateSales(no, salesRequestDto);
        return new BaseResponse<>("매출 번호 " + no + " 수정 완료");
    }

}
