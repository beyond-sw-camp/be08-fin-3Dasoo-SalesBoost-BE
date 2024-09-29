package beyond.samdasoo.plan.service;

import beyond.samdasoo.member.entity.Member;
import beyond.samdasoo.plan.dto.PlanRequestDto;
import beyond.samdasoo.plan.dto.PlanResponseDto;
import beyond.samdasoo.plan.dto.PlanUpdateDto;
import beyond.samdasoo.plan.entity.Plan;
import beyond.samdasoo.plan.repository.PlanRepository;
import beyond.samdasoo.todo.dto.TodoResponseDto;
import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    private final UserRepository userRepository;

    public PlanService(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    private Plan findPlanById(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("일정을 찾을 수 없습니다: " + id));
    }

    @Transactional
    public PlanResponseDto createPlan(PlanRequestDto planRequestDto) {

        User user = userRepository.findById(planRequestDto.getUserNo())
                .orElseThrow(() -> new EntityNotFoundException("회원 ID 조회 불가: " + planRequestDto.getUserNo()));

        Plan plan = Plan.builder()
                .personalYn(planRequestDto.getPersonalYn())
                .planCls(planRequestDto.getPlanCls())
                .planDate(planRequestDto.getPlanDate())
                .title(planRequestDto.getTitle())
                .startTime(planRequestDto.getStartTime())
                .endTime(planRequestDto.getEndTime())
                .content(planRequestDto.getContent())
                .userNo(user)
                .build();

        plan = planRepository.save(plan);
        return new PlanResponseDto(plan);
    }

    @Transactional(readOnly = true)
    public PlanResponseDto getPlanById(Long no) {
        return new PlanResponseDto(findPlanById(no));
    }

    @Transactional
    public void updatePlan(Long no, PlanUpdateDto planUpdateDto) {
        Plan plan = findPlanById(no);

        Optional.ofNullable(planUpdateDto.getPersonalYn()).ifPresent(plan::setPersonalYn);
        Optional.ofNullable(planUpdateDto.getPlanCls()).ifPresent(plan::setPlanCls);
        Optional.ofNullable(planUpdateDto.getPlanDate()).ifPresent(plan::setPlanDate);
        Optional.ofNullable(planUpdateDto.getTitle()).ifPresent(plan::setTitle);
        Optional.ofNullable(planUpdateDto.getStartTime()).ifPresent(plan::setStartTime);
        Optional.ofNullable(planUpdateDto.getEndTime()).ifPresent(plan::setEndTime);
        Optional.ofNullable(planUpdateDto.getContent()).ifPresent(plan::setContent);
    }

    public void deletePlan(Long no) {
        planRepository.delete(findPlanById(no));
    }
}
