package beyond.samdasoo.act.service;

import beyond.samdasoo.act.dto.ActRequestDto;
import beyond.samdasoo.act.dto.ActResponseDto;
import beyond.samdasoo.act.entity.Act;
import beyond.samdasoo.act.repository.ActRepository;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.lead.repository.LeadRepository;
import beyond.samdasoo.todo.dto.TodoResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.ACT_NOT_EXIST;

@Service
public class ActService {

    private final ActRepository actRepository;

    private final LeadRepository leadRepository;

    public ActService(ActRepository actRepository, LeadRepository leadRepository) {
        this.actRepository = actRepository;
        this.leadRepository = leadRepository;
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

//     TODO: 영업기회 엔티티 완성 후 주석 해제 예정
//    private Lead findLeadById(Long no) {
//        return leadRepository.findById(no)
//                .orElseThrow(() -> new EntityNotFoundException("영업기회 조회 불가: " + no));
//
//    }

    public ActResponseDto createAct(ActRequestDto actRequestDto) {

        Act act = Act.builder()
                .name(actRequestDto.getName())
//                  TODO: 영업기회 엔티티 완성 후 주석 해제 예정-createAct
//                .leadNo(findLeadById(actRequestDto.getLeadNo()))
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
    public void updateAct(Long no, ActRequestDto actRequestDto) {
        Act act = findActById(no);

        Optional.ofNullable(actRequestDto.getName()).ifPresent(act::setName);
//        TODO: 영업기회 엔티티 완성 후 주석 해제 예정-updateAct
//        act.setLeadNo(findLeadById(actRequestDto.getLeadNo()));
        Optional.ofNullable(actRequestDto.getCls()).ifPresent(act::setCls);
        Optional.ofNullable(actRequestDto.getPurpose()).ifPresent(act::setPurpose);
        Optional.ofNullable(actRequestDto.getActDate()).ifPresent(act::setActDate);
        Optional.ofNullable(actRequestDto.getStartTime()).ifPresent(act::setStartTime);
        Optional.ofNullable(actRequestDto.getEndTime()).ifPresent(act::setEndTime);
        Optional.ofNullable(actRequestDto.getCompleteYn()).ifPresent(act::setCompleteYn);
        Optional.ofNullable(actRequestDto.getPlanContent()).ifPresent(act::setPlanCont);
        Optional.ofNullable(actRequestDto.getActContent()).ifPresent(act::setActCont);
    }

    public void deleteAct(Long no) {actRepository.delete(findActById(no));}
}
