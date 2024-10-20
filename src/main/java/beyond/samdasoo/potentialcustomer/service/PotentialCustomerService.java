package beyond.samdasoo.potentialcustomer.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.common.utils.UserUtil;
import beyond.samdasoo.potentialcustomer.dto.*;
import beyond.samdasoo.potentialcustomer.entity.ContactHistory;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import beyond.samdasoo.potentialcustomer.repository.ContactHistoryRepository;
import beyond.samdasoo.potentialcustomer.repository.PotentialCustomerRepository;
import beyond.samdasoo.user.dto.UserRole;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class PotentialCustomerService {

    private final PotentialCustomerRepository potentialCustomerRepository;
    private final UserRepository userRepository;
    private final ContactHistoryRepository contactHistoryRepository;

    public void create(CreatePotentialCustomerReq request) {
        potentialCustomerRepository.save(request.toPotentialCustomer());
    }


    public PotentialCustomerDto getPotentialCustomer(Long id) {

        PotentialCustomer pCustomer = potentialCustomerRepository.findById(id)
                .orElseThrow(() -> new BaseException(POTENTIAL_CUSTOMER_NOT_EXIST));

        return PotentialCustomerDto.builder()
                .id(pCustomer.getId())
                .name(pCustomer.getName())
                .company(pCustomer.getCompany())
                .dept(pCustomer.getDept())
                .position(pCustomer.getPosition())
                .cls(pCustomer.getCls())
                .status(pCustomer.getContactStatus().getMessage())
                .grade(pCustomer.getGrade().getMessage())
                .phone(pCustomer.getPhone())
                .tel(pCustomer.getTel())
                .email(pCustomer.getEmail())
                .fax(pCustomer.getFax())
                .addr(pCustomer.getAddr())
                .note(pCustomer.getNote())
                .build();
    }


    public List<PotentialCustomerListDto> getAllPotentialCustomer() {
        List<PotentialCustomer> pCustomers = potentialCustomerRepository.findAll();

        return pCustomers.stream().map(p -> PotentialCustomerListDto.builder()
                .id(p.getId())
                .name(p.getName())
                .company(p.getCompany())
                .cls(p.getCls())
                .status(p.getContactStatus().getMessage())
                .phone(p.getPhone())
                .email(p.getEmail())
                .registerDate(p.getRegisterDate())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public void updatePotentialCustomer(Long prospectId, UpdatePotentialCustomerReq request) {
        PotentialCustomer pCustomer =potentialCustomerRepository.findById(prospectId)
                .orElseThrow(()-> new BaseException(POTENTIAL_CUSTOMER_NOT_EXIST));

        Optional.ofNullable(request.getName()).ifPresent(pCustomer::changeName);
        Optional.ofNullable(request.getCompany()).ifPresent(pCustomer::changeCompany);
        Optional.ofNullable(request.getPosition()).ifPresent(pCustomer::changePosition);
        Optional.ofNullable(request.getCls()).ifPresent(pCustomer::changeCls); // 접촉 구분
        Optional.ofNullable(request.getStatus()).ifPresent(pCustomer::changeStatus); // 접촉상태
        Optional.ofNullable(request.getGrade()).ifPresent(pCustomer::changeGrade); // 가망등급
        Optional.ofNullable(request.getPhone()).ifPresent(pCustomer::changePhone); // 휴대폰
        Optional.ofNullable(request.getTel()).ifPresent(pCustomer::changeTel); // 유선번호
        Optional.ofNullable(request.getFax()).ifPresent(pCustomer::changeFax); // 팩스
        Optional.ofNullable(request.getAddr()).ifPresent(pCustomer::changeAddr); // 주소
        Optional.ofNullable(request.getNote()).ifPresent(pCustomer::changeNote); // 비고
    }

    public void insertContactHistory(Long pCustomerId, CreateContactHistoryReq request) {
        PotentialCustomer pCustomer =potentialCustomerRepository.findById(pCustomerId)
                .orElseThrow(()-> new BaseException(POTENTIAL_CUSTOMER_NOT_EXIST));

        String userEmail = UserUtil.getLoginUserEmail();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(()-> new BaseException(USER_NOT_EXIST));

        // 임시 유저 (테스트용)
//        User tempUser = User.builder().name("홍길동").email("hong@naver.com").password("1234").role(UserRole.USER).build();
//        User saveUser = userRepository.save(tempUser);

        ContactHistory contactHistory = request.toContactHistory(user,pCustomer);

        contactHistoryRepository.save(contactHistory);
    }

    public void deleteContactHistory(Long historyId) {

        boolean isExist = contactHistoryRepository.existsById(historyId);
        if(isExist){
            contactHistoryRepository.deleteById(historyId);
        }else{
            throw new BaseException(CONTACT_HISTORY_NOT_EXIST);
        }
    }

    public List<ContactHistoryDto> getContactHistoryList(Long prospectId) {
        PotentialCustomer pCustomer = potentialCustomerRepository.findById(prospectId)
                .orElseThrow(() -> new BaseException(POTENTIAL_CUSTOMER_NOT_EXIST));
        List<ContactHistory> pCustomerList = contactHistoryRepository.findAllByPcustomer(pCustomer);

        return pCustomerList.stream().map(p->ContactHistoryDto.builder().id(p.getId()).contactDate(p.getContactDate())
                        .cls(p.getCls().getMessage()).content(p.getContent()).userName(p.getUser().getName()).build())
                .collect(Collectors.toList());


    }
}
