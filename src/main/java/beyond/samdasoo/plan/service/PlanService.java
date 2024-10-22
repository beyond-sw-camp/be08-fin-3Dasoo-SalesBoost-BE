package beyond.samdasoo.plan.service;

import beyond.samdasoo.calendar.repository.CalendarRepository;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.plan.dto.PlanRequestDto;
import beyond.samdasoo.plan.dto.PlanResponseDto;
import beyond.samdasoo.plan.dto.PlanUpdateDto;
import beyond.samdasoo.plan.entity.Plan;
import beyond.samdasoo.plan.repository.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.PLAN_NOT_EXIST;

@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final CalendarRepository calendarRepository;

    public PlanService(PlanRepository planRepository, CalendarRepository calendarRepository) {
        this.planRepository = planRepository;
        this.calendarRepository = calendarRepository;
    }

    private Plan findPlanById(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new BaseException(PLAN_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    public PlanResponseDto getPlanById(Long no) {
        Plan plan = findPlanById(no);
        return new PlanResponseDto(plan);
    }

    public List<PlanResponseDto> getAllPlans() throws BaseException {
        return planRepository.findAll().stream()
                .map(plan -> new PlanResponseDto(plan))
                .collect(Collectors.toList());
    }

    @Transactional
    public PlanResponseDto createPlan(PlanRequestDto planRequestDto) {

        Plan plan = Plan.builder()
                .calendar(calendarRepository.findCalendarById(planRequestDto.getCalendarNo()))
                .personalYn(planRequestDto.getPersonalYn())
                .planCls(planRequestDto.getPlanCls())
                .planDate(planRequestDto.getPlanDate())
                .title(planRequestDto.getTitle())
                .startTime(planRequestDto.getStartTime())
                .endTime(planRequestDto.getEndTime())
                .content(planRequestDto.getContent())
                .build();

        planRepository.save(plan);
        return new PlanResponseDto(plan);
    }

    @Transactional
    public PlanResponseDto updatePlan(Long no, PlanUpdateDto planUpdateDto) {
        Plan plan = findPlanById(no);

        plan.setCalendar(calendarRepository.findCalendarById(planUpdateDto.getCalendarNo()));
        Optional.ofNullable(planUpdateDto.getPersonalYn()).ifPresent(plan::setPersonalYn);
        Optional.ofNullable(planUpdateDto.getPlanCls()).ifPresent(plan::setPlanCls);
        Optional.ofNullable(planUpdateDto.getPlanDate()).ifPresent(plan::setPlanDate);
        Optional.ofNullable(planUpdateDto.getTitle()).ifPresent(plan::setTitle);
        Optional.ofNullable(planUpdateDto.getStartTime()).ifPresent(plan::setStartTime);
        Optional.ofNullable(planUpdateDto.getEndTime()).ifPresent(plan::setEndTime);
        Optional.ofNullable(planUpdateDto.getContent()).ifPresent(plan::setContent);

        planRepository.save(plan);
        return new PlanResponseDto(plan);
    }

    public void deletePlan(Long no) {
        planRepository.delete(findPlanById(no));
    }
}
