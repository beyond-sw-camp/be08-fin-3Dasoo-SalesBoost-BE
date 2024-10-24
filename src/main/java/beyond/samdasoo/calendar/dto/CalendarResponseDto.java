package beyond.samdasoo.calendar.dto;

import beyond.samdasoo.act.dto.ActResponseDto;
import beyond.samdasoo.act.entity.Act;
import beyond.samdasoo.calendar.entity.Calendar;
import beyond.samdasoo.plan.dto.PlanResponseDto;
import beyond.samdasoo.plan.entity.Plan;
import beyond.samdasoo.todo.dto.TodoResponseDto;
import beyond.samdasoo.todo.entity.Todo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CalendarResponseDto {

    private Long calendarNo;
    private String userName;
    private List<PlanResponseDto> plans;
    private List<TodoResponseDto> todos;
    private List<ActResponseDto> acts;

    public CalendarResponseDto(Calendar calendar) {
        this.calendarNo = calendar.getNo();
        this.userName = calendar.getUser().getName();

        this.plans = (calendar.getPlans() != null) ?
                calendar.getPlans().stream().map(PlanResponseDto::new).collect(Collectors.toList()) :
                new ArrayList<>();
        this.todos = (calendar.getTodos() != null) ?
                calendar.getTodos().stream().map(TodoResponseDto::new).collect(Collectors.toList()) :
                new ArrayList<>();
        this.acts = (calendar.getActs() != null) ?
                calendar.getActs().stream().map(ActResponseDto::new).collect(Collectors.toList()) :
                new ArrayList<>();
    }

    public CalendarResponseDto(Calendar calendar, List<Todo> todos, List<Plan> plans, List<Act> acts) {
        this.calendarNo = calendar.getNo();
        this.userName = calendar.getUser().getName();
        this.todos = todos.stream().map(TodoResponseDto::new).collect(Collectors.toList());
        this.plans = plans.stream().map(PlanResponseDto::new).collect(Collectors.toList());
        this.acts = acts.stream().map(ActResponseDto::new).collect(Collectors.toList());
    }
}

