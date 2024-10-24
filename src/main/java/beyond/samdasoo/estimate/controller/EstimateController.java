package beyond.samdasoo.estimate.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.estimate.dto.CreateEstimateDto;
import beyond.samdasoo.estimate.dto.EstimateRequestDto;
import beyond.samdasoo.estimate.dto.EstimateResponseDto;
import beyond.samdasoo.estimate.service.EstimateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estimates")
@Tag(name="Estimate APIs",description = "견적 관련 API")
public class EstimateController {

    private final EstimateService estimateService;

    @Autowired
    public EstimateController(EstimateService estimateService) {
        this.estimateService = estimateService;
    }

    @PostMapping
    @Operation(summary = "견적 등록",description = "새로운 견적 건을 등록")
    public BaseResponse<EstimateResponseDto> createEstimate(@RequestBody CreateEstimateDto createEstimateDto) {
        EstimateResponseDto responseDto = estimateService.createEstimate(createEstimateDto);
        return new BaseResponse<>(responseDto);
    }


    @GetMapping
    @Operation(summary = "모든 견적 조회",description = "등록되어 있는 모든 견적 조회")
    public BaseResponse<List<EstimateResponseDto>> getAllEstimates() {
        List<EstimateResponseDto> responseDtos = estimateService.getAllEstimates();
        return new BaseResponse<>(responseDtos);
    }


    @GetMapping("/{no}")
    @Operation(summary = "특정 견적 조회",description = "견적 번호로 특정 제안 조회")
    public BaseResponse<EstimateResponseDto> getEstimateById(@PathVariable("no") Long estNo) {
        EstimateResponseDto responseDto = estimateService.getEstimateById(estNo);
        return new BaseResponse<>(responseDto);
    }

    @PatchMapping("/{no}")
    @Operation(summary = "견적 수정",description = "견적 번호로 특정 제안 수정")
    public BaseResponse<EstimateResponseDto> patchUpdateEstimate(@PathVariable("no") Long estNo,
                                                                 @RequestBody EstimateRequestDto estimateRequestDto) {
        EstimateResponseDto responseDto = estimateService.updateEstimate(estNo, estimateRequestDto);
        return new BaseResponse<>(responseDto);
    }
    @DeleteMapping("/{no}")
    @Operation(summary = "견적 삭제",description = "견적 번호로 특정 제안 삭제")
    public BaseResponse<String> deleteEstimate(@PathVariable("no") Long estNo) {
        estimateService.deleteEstimate(estNo);
        return new BaseResponse<>("견적이 삭제되었습니다.");
    }

}
