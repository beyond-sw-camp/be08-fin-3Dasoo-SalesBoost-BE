package beyond.samdasoo.estimate.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.estimate.dto.EstProductRequestDto;
import beyond.samdasoo.estimate.dto.EstProductResponseDto;
import beyond.samdasoo.estimate.service.EstProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/estProducts")
@Tag(name = "Estimate Product APIs", description = "견적별 제품 관련 API")
@Slf4j
public class EstProductController {

    private final EstProductService estProductService;

    @PostMapping
    @Operation(summary = "견적별 물품 등록", description = "새로운 견적별 물품 건을 등록")
    public BaseResponse<EstProductResponseDto> createEstProduct(@RequestBody EstProductRequestDto estProductRequestDto) {
        EstProductResponseDto responseDto = estProductService.createEstProduct(estProductRequestDto);
        return new BaseResponse<>(responseDto);
    }

    @GetMapping("/{no}")
    @Operation(summary = "견적별 물품 조회", description = "견적 내 등록되어 있는 제품 조회")
    public BaseResponse<List<EstProductResponseDto>> getEstProducts(@PathVariable("no") Long estNo) {
        List<EstProductResponseDto> responseDtos = estProductService.getEstProductsByEstimate(estNo);

        return new BaseResponse<>(responseDtos);
    }
}
