package beyond.samdasoo.potentialcustomer.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.common.utils.UserUtil;
import beyond.samdasoo.potentialcustomer.dto.*;
import beyond.samdasoo.potentialcustomer.entity.ContactHistory;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import beyond.samdasoo.potentialcustomer.repository.ContactHistoryRepository;
import beyond.samdasoo.potentialcustomer.repository.PotentialCustomerRepository;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .status(pCustomer.getStatus().getCode())
                .grade(pCustomer.getGrade().getCode())
                .phone(pCustomer.getPhone())
                .tel(pCustomer.getTel())
                .email(pCustomer.getEmail())
                .fax(pCustomer.getFax())
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
                .status(p.getStatus().getCode())
                .phone(p.getPhone())
                .email(p.getEmail())
                .createdDate(p.getCreatedAt())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public void updatePotentialCustomer(Long prospectId, UpdatePotentialCustomerReq request) {
        PotentialCustomer pCustomer =potentialCustomerRepository.findById(prospectId)
                .orElseThrow(()-> new BaseException(POTENTIAL_CUSTOMER_NOT_EXIST));

        Optional.ofNullable(request.getName()).ifPresent(pCustomer::changeName);
        Optional.ofNullable(request.getCompany()).ifPresent(pCustomer::changeCompany);
        Optional.ofNullable(request.getPosition()).ifPresent(pCustomer::changePosition);
        Optional.ofNullable(request.getCls()).ifPresent(pCustomer::changeCls);
        pCustomer.changeCls(request.getCls());
        pCustomer.changeStatus(request.getStatus());
        Optional.ofNullable(request.getGrade()).ifPresent(pCustomer::changeGrade);
        Optional.ofNullable(request.getPhone()).ifPresent(pCustomer::changePhone);
        Optional.ofNullable(request.getTel()).ifPresent(pCustomer::changeTel);
        Optional.ofNullable(request.getFax()).ifPresent(pCustomer::changeFax);
        Optional.ofNullable(request.getAddr()).ifPresent(pCustomer::changeAddr);
        Optional.ofNullable(request.getNote()).ifPresent(pCustomer::changeNote);

//        return PotentialCustomerDto.builder().name(request.getName())
//                .company(request.getCompany())
//                .cls(request.getCls())
//                .status(request.getStatus())
//                .grade(request.getGrade())
//                .phone(request.getPhone())
//                .tel(request.getTel())
//                .email(request.getEmail())
//                .fax(request.getFax())
//                .note(request.getNote())
//                .build();

    }

    public void insertContactHistory(Long pCustomerId, CreateContactHistoryReq request) {
        PotentialCustomer pCustomer =potentialCustomerRepository.findById(pCustomerId)
                .orElseThrow(()-> new BaseException(POTENTIAL_CUSTOMER_NOT_EXIST));

        String userEmail = UserUtil.getLoginUserEmail();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(()-> new BaseException(USER_NOT_EXIST));

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

    public void getContactHistoryList(Long prospectId) {
        PotentialCustomer pCustomer = potentialCustomerRepository.findById(prospectId)
                .orElseThrow(() -> new BaseException(POTENTIAL_CUSTOMER_NOT_EXIST));
    //    List<ContactHistory> allByPCustomer = contactHistoryRepository.findByPCustomer(pCustomer);


    }
}