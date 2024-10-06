package beyond.samdasoo.plan.controller;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.plan.dto.PlanRequestDto;
import beyond.samdasoo.plan.dto.PlanResponseDto;
import beyond.samdasoo.plan.dto.PlanUpdateDto;
import beyond.samdasoo.plan.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@Tag(name="Plans APIs", description = "일정 API")
public class PlanController {
    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) { this.planService = planService; }


    @PostMapping
    @Operation(summary = "일정 등록", description = "새로운 일정 등록")
    public ResponseEntity<BaseResponse<PlanResponseDto>> createPlan(@RequestBody PlanRequestDto planRequestDto) {
        PlanResponseDto responseDto = planService.createPlan(planRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(responseDto));
    }

    @GetMapping("/{no}")
    @Operation(summary = "일정 조회", description = "특정 일정 조회")
    public ResponseEntity<BaseResponse<PlanResponseDto>> getPlanById(@PathVariable("no") Long no) {
        try{
            PlanResponseDto responseDto = planService.getPlanById(no);
            return ResponseEntity.ok(new BaseResponse<>(responseDto));
        }catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }

    @GetMapping
    @Operation(summary = "전체 일정 조회", description = "모든 일정 조회")
    public ResponseEntity<BaseResponse<List<PlanResponseDto>>> getAllPlans() {
        try {
            List<PlanResponseDto> plans = planService.getAllPlans();
            return ResponseEntity.ok(new BaseResponse<>(plans));
        } catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }

    @PatchMapping("/{no}")
    @Operation(summary = "일정 수정", description = "특정 일정 수정")
    public ResponseEntity<BaseResponse<String>> patchUpdatePlan(@PathVariable("no") Long no, @RequestBody PlanUpdateDto planUpdateDto) {
        try{
            planService.updatePlan(no, planUpdateDto);
            return ResponseEntity.ok(new BaseResponse<>("일정을 수정하였습니다"));
        } catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }

    @DeleteMapping("/{no}")
    @Operation(summary = "일정 삭제", description = "특정 일정 삭제")
    public ResponseEntity<BaseResponse<String>> deletePlan(@PathVariable("no") Long no) {
        try{
            planService.deletePlan(no);
            return ResponseEntity.ok(new BaseResponse<>("일정 삭제에 성공했습니다."));
        } catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }
}