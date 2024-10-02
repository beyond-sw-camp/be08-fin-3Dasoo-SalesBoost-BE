package beyond.samdasoo.plan.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.plan.dto.PlanRequestDto;
import beyond.samdasoo.plan.dto.PlanResponseDto;
import beyond.samdasoo.plan.dto.PlanTodoResponseDto;
import beyond.samdasoo.plan.dto.PlanUpdateDto;
import beyond.samdasoo.plan.entity.Plan;
import beyond.samdasoo.plan.repository.PlanRepository;
import beyond.samdasoo.todo.entity.Todo;
import beyond.samdasoo.todo.repository.TodoRepository;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.PLAN_NOT_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.USER_NOT_EXIST;

@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public PlanService(PlanRepository planRepository, UserRepository userRepository, TodoRepository todoRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    private Plan findPlanById(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new BaseException(PLAN_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    public List<PlanTodoResponseDto> getPlansWithTodo(Long no) {

        Optional<Plan> optionalPlan = planRepository.findById(no);

        if (optionalPlan.isEmpty()) {
            throw new BaseException(PLAN_NOT_EXIST);
        }

        Plan plan = optionalPlan.get();
        List<PlanTodoResponseDto> result = new ArrayList<>();

        if (!plan.getTodos().isEmpty()) {
            List<String> todoTitles = plan.getTodos().stream()
                    .map(Todo::getTitle)
                    .collect(Collectors.toList());
            result.add(new PlanTodoResponseDto(plan.getNo(), plan.getPlanDate(), todoTitles));
        }

        return result;
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
    public PlanResponseDto getPlanById(Long no) { return new PlanResponseDto(findPlanById(no)); }

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
