package beyond.samdasoo.sales.controller;

import beyond.samdasoo.sales.dto.SalesRequestDto;
import beyond.samdasoo.sales.dto.SalesResponseDto;
import beyond.samdasoo.sales.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/sales")
public class SalesController {

    private final SalesService salesService;

    // 매출 전체 조회
    @GetMapping
    public List<SalesResponseDto> getSales() {
        return salesService.getAllSales();
    }

    // 특정 매출 조회
    @GetMapping("/{no}")
    public SalesResponseDto getSales(@PathVariable("no") Long no) {
        return salesService.getSales(no);
    }

    // 매출 생성
    @PostMapping
    public SalesResponseDto createSales(@RequestBody SalesRequestDto salesRequestDto) {
        return salesService.createSales(salesRequestDto);
    }

    // 매출 삭제
    @DeleteMapping("/{no}")
    public void deleteSales(@PathVariable("no") Long no) {
        salesService.deleteSales(no);
    }

    // 매출 수정
    @PatchMapping("/{no}")
    public void updateSales(@PathVariable("no") Long no, @RequestBody SalesRequestDto salesRequestDto) {
        salesService.updateSales(no, salesRequestDto);
    }

}
