package beyond.samdasoo.act.service;

import beyond.samdasoo.act.dto.ActRequestDto;
import beyond.samdasoo.act.dto.ActResponseDto;
import beyond.samdasoo.act.entity.Act;
import beyond.samdasoo.act.repository.ActRepository;
import beyond.samdasoo.calendar.repository.CalendarRepository;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.lead.Entity.Lead;
import beyond.samdasoo.lead.repository.LeadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.ACT_NOT_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.LEAD_NOT_EXIST;

@Service
public class ActService {

    private final ActRepository actRepository;
    private final LeadRepository leadRepository;
    private final CalendarRepository calendarRepository;

    public ActService(ActRepository actRepository, LeadRepository leadRepository, CalendarRepository calendarRepository) {
        this.actRepository = actRepository;
        this.leadRepository = leadRepository;
        this.calendarRepository = calendarRepository;
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
}
