package beyond.samdasoo.potentialcustomer.service;

import beyond.samdasoo.common.dto.SearchCond;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.utils.UserUtil;
import beyond.samdasoo.customer.entity.Customer;
import beyond.samdasoo.customer.repository.CustomerRepository;
import beyond.samdasoo.potentialcustomer.dto.*;
import beyond.samdasoo.potentialcustomer.entity.ContactHistory;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import beyond.samdasoo.potentialcustomer.repository.ContactHistoryRepository;
import beyond.samdasoo.potentialcustomer.repository.PotentialCustomerRepository;
import beyond.samdasoo.potentialcustomer.repository.PotentialCustomerRepositoryCustom;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class PotentialCustomerService {

    private final PotentialCustomerRepository potentialCustomerRepository;
    private final UserRepository userRepository;
    private final ContactHistoryRepository contactHistoryRepository;
    private final CustomerRepository customerRepository;
    private final PotentialCustomerRepositoryCustom potentialCustomerRepositoryCustom;

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

    @Transactional
    public void updatePotentialCustomer(Long prospectId, UpdatePotentialCustomerReq request) {
        PotentialCustomer pCustomer = potentialCustomerRepository.findById(prospectId)
                .orElseThrow(() -> new BaseException(POTENTIAL_CUSTOMER_NOT_EXIST));

        // 접촉상태가 '고객전환'일 경우 고객으로 전환
        if(Objects.equals(request.getStatus(), PotentialCustomer.ContactStatus.CONVERT_CUSTOMER.getMessage())){
            String loginUserEmail = UserUtil.getLoginUserEmail();
            User user = userRepository.findByEmail(loginUserEmail).get();
            Customer newCustomer = convertToCustomer(pCustomer,user);
            customerRepository.save(newCustomer);
        }

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

    private Customer convertToCustomer(PotentialCustomer pCustomer,User user) {
        // 등급이 x 일경우 즉 미선택일 경우는 A 등급으로 넣어주고 아니면 원래 등급으로
        Customer.Grade grade = Customer.Grade.A;
        if(pCustomer.getGrade() != PotentialCustomer.Grade.X){
            grade = Customer.Grade.getGradeByMessage(pCustomer.getGrade().getMessage());
        }
        return Customer.builder()
                .name(pCustomer.getName())
                .company(pCustomer.getCompany())
                .dept(pCustomer.getDept())
                .position(pCustomer.getPosition())
                .phone(pCustomer.getPhone())
                .email(pCustomer.getEmail())
                .grade(grade)
                .potentialCustomer(pCustomer)
                .user(user)
                .build();

    }

    public void insertContactHistory(Long pCustomerId, CreateContactHistoryReq request) {
        PotentialCustomer pCustomer = potentialCustomerRepository.findById(pCustomerId)
                .orElseThrow(() -> new BaseException(POTENTIAL_CUSTOMER_NOT_EXIST));

        String userEmail = UserUtil.getLoginUserEmail();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BaseException(USER_NOT_EXIST));

        // 임시 유저 (테스트용)
//        User tempUser = User.builder().name("홍길동").email("hong@naver.com").password("1234").role(UserRole.USER).build();
//        User saveUser = userRepository.save(tempUser);

        ContactHistory contactHistory = request.toContactHistory(user, pCustomer);

        contactHistoryRepository.save(contactHistory);
    }

    public void deleteContactHistory(Long historyId) {
        boolean isExist = contactHistoryRepository.existsById(historyId);
        if (isExist) {
            contactHistoryRepository.deleteById(historyId);
        } else {
            throw new BaseException(CONTACT_HISTORY_NOT_EXIST);
        }
    }



    public List<PotentialCustomersGetRes> getAllPotentialCustomer() {
        List<PotentialCustomer> pCustomers = potentialCustomerRepository.findAll();

        return pCustomers.stream().map(p -> PotentialCustomersGetRes.builder()
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

    public List<PotentialCustomersGetRes> getListByFilter(SearchCriteriaDTO searchCriteria) {
        List<PotentialCustomer> potentialCustomers;

        if ((searchCriteria.getSearchQuery() == null || searchCriteria.getSearchQuery().isEmpty()) &&
                (searchCriteria.getSearchQuery() == null || searchCriteria.getSearchQuery().isEmpty()) &&
                (searchCriteria.getSelectedContact().equals("전체"))) {
            potentialCustomers = potentialCustomerRepository.findAll();
        }else{
            if(searchCriteria.getSelectedContact().equals("전체")){
                potentialCustomers = potentialCustomerRepository.searchByCriteria(searchCriteria.getSelectedItem(),
                        searchCriteria.getSearchQuery(),
                        null);
            }else {
                potentialCustomers = potentialCustomerRepository.searchByCriteria(searchCriteria.getSelectedItem(),
                        searchCriteria.getSearchQuery(),
                        PotentialCustomer.ContactStatus.getStatus(searchCriteria.getSelectedContact()));
            }
        }
        return potentialCustomers.stream().map(p -> PotentialCustomersGetRes.builder()
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



    public List<ContactHistoryDto> getContactHistoryList(Long prospectId) {
        PotentialCustomer pCustomer = potentialCustomerRepository.findById(prospectId)
                .orElseThrow(() -> new BaseException(POTENTIAL_CUSTOMER_NOT_EXIST));
        List<ContactHistory> pCustomerList = contactHistoryRepository.findAllByPcustomer(pCustomer);

        return pCustomerList.stream().map(p -> ContactHistoryDto.builder().id(p.getId()).contactDate(p.getContactDate())
                        .cls(p.getCls().getMessage()).content(p.getContent()).userName(p.getUser().getName()).build())
                .collect(Collectors.toList());
    }

    public Long getPotentialCustomerCount(SearchCond searchCond) {
        return potentialCustomerRepositoryCustom.getPotentialCustomerCount(
                searchCond.getSearchDate(), searchCond.getUserNo()
        );
    }
}
