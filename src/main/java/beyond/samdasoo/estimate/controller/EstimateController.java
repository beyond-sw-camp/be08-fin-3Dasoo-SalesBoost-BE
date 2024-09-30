package beyond.samdasoo.estimate.controller;

import beyond.samdasoo.estimate.dto.EstimateRequestDto;
import beyond.samdasoo.estimate.dto.EstimateResponseDto;
import beyond.samdasoo.estimate.service.EstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estimates")
public class EstimateController {

    private final EstimateService estimateService;

    @Autowired
    public EstimateController(EstimateService estimateService) {
        this.estimateService = estimateService;
    }

    @PostMapping
    public ResponseEntity<EstimateResponseDto> createEstimate(@RequestBody EstimateRequestDto estimateRequestDto) {
        EstimateResponseDto responseDto = estimateService.createEstimate(estimateRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<EstimateResponseDto>> getAllEstimates() {
        List<EstimateResponseDto> responseDtos = estimateService.getAllEstimates();
        return ResponseEntity.ok(responseDtos);
    }


    @GetMapping("/{no}")
    public ResponseEntity<EstimateResponseDto> getEstimateById(@PathVariable("no") Long estNo) {
        EstimateResponseDto responseDto = estimateService.getEstimateById(estNo);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{no}")
    public ResponseEntity<EstimateResponseDto> patchUpdateEstimate(@PathVariable("no") Long estNo,
                                                              @RequestBody EstimateRequestDto estimateRequestDto) {
        EstimateResponseDto responseDto = estimateService.updateEstimate(estNo, estimateRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<Void> deleteEstimate(@PathVariable("no") Long estNo) {
        estimateService.deleteEstimate(estNo);
        return ResponseEntity.noContent().build();
    }

}
