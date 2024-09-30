package beyond.samdasoo.plan.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.plan.dto.PlanRequestDto;
import beyond.samdasoo.plan.dto.PlanResponseDto;
import beyond.samdasoo.plan.dto.PlanUpdateDto;
import beyond.samdasoo.plan.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static beyond.samdasoo.common.response.BaseResponseStatus.PLAN_NOT_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.TODO_NOT_EXIST;

@RestController
@RequestMapping("/api/plans")
@Tag(name="Plans APIs", description = "일정 API")
public class PlanController {
    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) { this.planService = planService; }


    @PostMapping
    @Operation(summary = "일정 등록", description = "새로운 일정 등록")
    public BaseResponse<PlanResponseDto> createPlan(@RequestBody PlanRequestDto planRequestDto) {
        PlanResponseDto responseDto = planService.createPlan(planRequestDto);
        return new BaseResponse<>(responseDto);
    }

    @GetMapping("/{no}")
    @Operation(summary = "일정 조회", description = "특정 일정 조회")
    public BaseResponse<PlanResponseDto> getPlanById(@PathVariable("no") Long no) {
        PlanResponseDto responseDto = planService.getPlanById(no);
        return new BaseResponse<>(responseDto);
    }

    @PatchMapping("/{no}")
    @Operation(summary = "일정 수정", description = "특정 일정 수정")
    public BaseResponse<String> patchUpdatePlan(@PathVariable("no") Long no, @RequestBody PlanUpdateDto planUpdateDto) {
        try{
            planService.updatePlan(no, planUpdateDto);
            return new BaseResponse<>("일정을 수정하였습니다.");
        }catch (Exception e) {
            return new BaseResponse<>(PLAN_NOT_EXIST);
        }
    }

    @DeleteMapping("/{no}")
    @Operation(summary = "일정 삭제", description = "특정 일정 삭제")
    public BaseResponse<String> deletePlan(@PathVariable("no") Long no) {
        try{
            planService.deletePlan(no);
            return new BaseResponse<>("일정 삭제에 성공했습니다.");
        }catch (Exception e) {
            return new BaseResponse<>(PLAN_NOT_EXIST);
        }
    }
}