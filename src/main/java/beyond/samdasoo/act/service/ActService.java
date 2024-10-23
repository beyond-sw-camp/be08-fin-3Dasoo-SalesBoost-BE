package beyond.samdasoo.act.service;

import beyond.samdasoo.act.dto.ActRequestDto;
import beyond.samdasoo.act.dto.ActResponseDto;
import beyond.samdasoo.act.dto.ActStatusDto;
import beyond.samdasoo.act.entity.Act;
import beyond.samdasoo.act.entity.QAct;
import beyond.samdasoo.act.repository.ActRepository;
import beyond.samdasoo.calendar.repository.CalendarRepository;
import beyond.samdasoo.common.dto.SearchCond;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.lead.Entity.Lead;
import beyond.samdasoo.lead.repository.LeadRepository;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.ACT_NOT_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.LEAD_NOT_EXIST;

@Service
public class ActService {

    private final ActRepository actRepository;
    private final LeadRepository leadRepository;
    private final CalendarRepository calendarRepository;
    private final JPAQueryFactory queryFactory;

    public ActService(ActRepository actRepository, LeadRepository leadRepository, CalendarRepository calendarRepository, JPAQueryFactory queryFactory) {
        this.actRepository = actRepository;
        this.leadRepository = leadRepository;
        this.calendarRepository = calendarRepository;
        this.queryFactory = queryFactory;
    }

    public Act findActById(Long no) {
        return actRepository.findById(no)
                .orElseThrow(() -> new BaseException(ACT_NOT_EXIST));
    }

    public List<ActResponseDto> getAllActs() throws BaseException {
        return actRepository.findAll().stream()
                .map(act -> new ActResponseDto(act))
                .collect(Collectors.toList());
    }

    private Lead findLeadById(Long no) {
        return leadRepository.findById(no)
                .orElseThrow(() -> new BaseException(LEAD_NOT_EXIST));

    }

    public ActResponseDto createAct(ActRequestDto actRequestDto) {

        Act act = Act.builder()
                .name(actRequestDto.getName())
                .lead(findLeadById(actRequestDto.getLeadNo()))
                .calendar(calendarRepository.findCalendarById(actRequestDto.getCalendarNo()))
                .cls(actRequestDto.getCls())
                .purpose(actRequestDto.getPurpose())
                .actDate(actRequestDto.getActDate())
                .startTime(actRequestDto.getStartTime())
                .endTime(actRequestDto.getEndTime())
                .completeYn(actRequestDto.getCompleteYn())
                .planCont(actRequestDto.getPlanContent())
                .actCont(actRequestDto.getActContent())
                .build();

        act = actRepository.save(act);
        return new ActResponseDto(act);
    }

    @Transactional(readOnly = true)
    public ActResponseDto getActById(Long no) {return new ActResponseDto(findActById(no));}

    @Transactional
    public ActResponseDto updateAct(Long no, ActRequestDto actRequestDto) {
        Act act = findActById(no);

        act.setLead(findLeadById(actRequestDto.getLeadNo()));
        act.setCalendar(calendarRepository.findCalendarById(actRequestDto.getCalendarNo()));
        Optional.ofNullable(actRequestDto.getName()).ifPresent(act::setName);
        Optional.ofNullable(actRequestDto.getCls()).ifPresent(act::setCls);
        Optional.ofNullable(actRequestDto.getPurpose()).ifPresent(act::setPurpose);
        Optional.ofNullable(actRequestDto.getActDate()).ifPresent(act::setActDate);
        Optional.ofNullable(actRequestDto.getStartTime()).ifPresent(act::setStartTime);
        Optional.ofNullable(actRequestDto.getEndTime()).ifPresent(act::setEndTime);
        Optional.ofNullable(actRequestDto.getCompleteYn()).ifPresent(act::setCompleteYn);
        Optional.ofNullable(actRequestDto.getPlanContent()).ifPresent(act::setPlanCont);
        Optional.ofNullable(actRequestDto.getActContent()).ifPresent(act::setActCont);

        actRepository.save(act);
        return new ActResponseDto(act);

    }

    public void deleteAct(Long no) {actRepository.delete(findActById(no));}

    public Map<String, Long> countActsCategory() {
        QAct act = QAct.act;

        return queryFactory
                .select(act.cls, act.count())
                .from(act)
                .groupBy(act.cls)
                .fetch()
                .stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(act.cls).name(),
                        tuple -> tuple.get(act.count())
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> countMonthlyActs(int year) {
        QAct act = QAct.act;
        NumberTemplate<Integer> monthTemplate = Expressions.numberTemplate(Integer.class, "month({0})", act.actDate);
        NumberTemplate<Integer> yearTemplate = Expressions.numberTemplate(Integer.class, "year({0})", act.actDate);

        return queryFactory
                .select(monthTemplate, act.count())
                .from(act)
                .where(yearTemplate.eq(year))
                .groupBy(monthTemplate)
                .fetch()
                .stream()
                .collect(Collectors.toMap(
                        tuple -> year + "-" + String.format("%02d", tuple.get(monthTemplate)),
                        tuple -> tuple.get(act.count())
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Double> activityRatio() {
        QAct act = QAct.act;

        long totalActivities = queryFactory
                .selectFrom(act)
                .fetchCount();

        return queryFactory
                .select(act.calendar.user.name, act.count())
                .from(act)
                .groupBy(act.calendar.user.name)
                .fetch()
                .stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(act.calendar.user.name),
                        tuple -> Math.round((double) tuple.get(act.count()) / totalActivities * 100 * 100) / 100.0
                ));
    }

    @Transactional(readOnly = true)
    public ActStatusDto getActStatus(SearchCond searchCond) {
        return actRepository.findActStatus(searchCond.getSearchDate(), searchCond.getUserNo());
    }
}
