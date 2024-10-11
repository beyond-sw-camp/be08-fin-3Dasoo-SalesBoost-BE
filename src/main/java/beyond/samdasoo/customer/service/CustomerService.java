package beyond.samdasoo.customer.service;

import beyond.samdasoo.customer.dto.CustomerCreateReq;
import beyond.samdasoo.customer.entity.Customer;
import beyond.samdasoo.customer.repository.CustomerRepository;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public void create(CustomerCreateReq req) {

        // 임시 유저 사용 (테스트 유저)
        User testUser = userRepository.findByEmail("test@naver.com").get();



        Customer customer = Customer.builder()
                .name(req.getName())
                .company(req.getCompany())
                .dept(req.getDept())
                .position(req.getPosition())
                .email(req.getEmail())
                .phone(req.getPhone())
                .tel(req.getTel())
                .grade(Customer.Grade.getGradeByMessage(req.getGrade()))
                .isKeyMan(req.isKeyman())
                .user(testUser)
                .build();
        customerRepository.save(customer);
    }

}
