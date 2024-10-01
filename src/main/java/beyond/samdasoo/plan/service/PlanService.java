package beyond.samdasoo.plan.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.plan.dto.PlanRequestDto;
import beyond.samdasoo.plan.dto.PlanResponseDto;
import beyond.samdasoo.plan.dto.PlanUpdateDto;
import beyond.samdasoo.plan.entity.Plan;
import beyond.samdasoo.plan.repository.PlanRepository;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static beyond.samdasoo.common.response.BaseResponseStatus.PLAN_NOT_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.USER_NOT_EXIST;

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
                .orElseThrow(() -> new BaseException(PLAN_NOT_EXIST));
    }

    @Transactional
    public PlanResponseDto createPlan(PlanRequestDto planRequestDto) {

        User user = userRepository.findById(planRequestDto.getUser())
                .orElseThrow(() -> new BaseException(USER_NOT_EXIST));

        Plan plan = Plan.builder()
                .personalYn(planRequestDto.getPersonalYn())
                .planCls(planRequestDto.getPlanCls())
                .planDate(planRequestDto.getPlanDate())
                .title(planRequestDto.getTitle())
                .startTime(planRequestDto.getStartTime())
                .endTime(planRequestDto.getEndTime())
                .content(planRequestDto.getContent())
                .user(user)
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
