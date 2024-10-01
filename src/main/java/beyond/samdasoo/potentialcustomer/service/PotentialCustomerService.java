package beyond.samdasoo.potentialcustomer.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.potentialcustomer.dto.CreatePotentialCustomerReq;
import beyond.samdasoo.potentialcustomer.dto.PotentialCustomerDto;
import beyond.samdasoo.potentialcustomer.dto.PotentialCustomerListDto;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import beyond.samdasoo.potentialcustomer.repository.PotentialCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.POTENTIAL_CUSTOMER_NOT_EXIST;

@RequiredArgsConstructor
@Service
public class PotentialCustomerService {

    private final PotentialCustomerRepository potentialCustomerRepository;

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
}
