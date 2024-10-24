package beyond.samdasoo.sales.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.sales.dto.SalesPredictionDto;
import beyond.samdasoo.sales.dto.SalesRequestDto;
import beyond.samdasoo.sales.dto.SalesResponseDto;
import beyond.samdasoo.sales.service.SalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    // 특정 년도 매출 조회
    @GetMapping("/count/monthly")
    @Operation(summary = "특정 년도 매출 조회", description = "특정 년도에 대한 월별 매출 데이터 조회")
    public BaseResponse<Map<String, Integer>> getMonthlySalesData(@RequestParam("year") String year) {
        Map<String, Integer> monthlySales = salesService.getMonthlySalesData(year);
        return new BaseResponse<>(monthlySales);
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

    // 매출 예측 api 호출 (년도별)
    @GetMapping("/forecast/years")
    @Operation(summary = "년도별 매출 예측", description = "년도별 매출 예측 데이터 조회")
    public BaseResponse<List<SalesPredictionDto>> getAnnualSalesForecast() {
        List<SalesResponseDto> sales = salesService.getAllSales();  // 모든 매출 데이터를 가져옴

        // 매출 데이터를 파이썬 API로 전달하여 예측 데이터 가져옴
        List<SalesPredictionDto> predictions = salesService.getYearsPredictedSales(sales);  // 년도별 예측된 매출 리스트

        return new BaseResponse<>(predictions);  // 예측된 DTO 리스트 반환
    }

    // 매출 예측 api 호출 (월별)
    @GetMapping("/forecast/month")
    @Operation(summary = "월별 매출 예측", description = "월별 매출 예측 데이터 조회")
    public BaseResponse<List<SalesPredictionDto>> getMonthlySalesForecast() {
        List<SalesResponseDto> sales = salesService.getAllSales();  // 모든 매출 데이터를 가져옴

        // 매출 데이터를 파이썬 API로 전달하여 예측 데이터 가져옴
        List<SalesPredictionDto> predictions = salesService.getMonthlyPredictedSales(sales);  // 월별 예측된 매출 리스트

        return new BaseResponse<>(predictions);  // 예측된 DTO 리스트 반환
    }

    // 매출 예측 api 호출 (분기별)
    @GetMapping("/forecast/quarter")
    @Operation(summary = "분기별 매출 예측", description = "분기별 매출 예측 데이터 조회")
    public BaseResponse<List<SalesPredictionDto>> getQuarterlySalesForecast() {
        List<SalesResponseDto> sales = salesService.getAllSales();  // 모든 매출 데이터를 가져옴

        // 매출 데이터를 파이썬 API로 전달하여 예측 데이터 가져옴
        List<SalesPredictionDto> predictions = salesService.getQuarterlyPredictedSales(sales);  // 분기별 예측된 매출 리스트

        return new BaseResponse<>(predictions);  // 예측된 DTO 리스트 반환
    }
}
