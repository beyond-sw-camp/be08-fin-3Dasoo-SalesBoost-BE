package beyond.samdasoo.plan.controller;

import beyond.samdasoo.plan.dto.PlanRequestDto;
import beyond.samdasoo.plan.dto.PlanResponseDto;
import beyond.samdasoo.plan.dto.PlanUpdateDto;
import beyond.samdasoo.plan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) { this.planService = planService; }


    @PostMapping
    public ResponseEntity<PlanResponseDto> createPlan(@RequestBody PlanRequestDto planRequestDto) {
        PlanResponseDto responseDto = planService.createPlan(planRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{no}")
    public ResponseEntity<PlanResponseDto> getPlanById(@PathVariable("no") Long no) {
        PlanResponseDto responseDto = planService.getPlanById(no);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{no}")
    public ResponseEntity<Void> patchUpdatePlan(@PathVariable("no") Long no, @RequestBody PlanUpdateDto planUpdateDto) {
        planService.updatePlan(no, planUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<Void> deletePlan(@PathVariable("no") Long no) {
        planService.deletePlan(no);
        return ResponseEntity.noContent().build();
    }
}