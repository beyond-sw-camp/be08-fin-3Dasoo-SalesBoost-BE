package beyond.samdasoo.potentialcustomer.service;

import beyond.samdasoo.potentialcustomer.dto.CreatePotentialCustomerReq;
import beyond.samdasoo.potentialcustomer.repository.PotentialCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PotentialCustomerService {

    private final PotentialCustomerRepository potentialCustomerRepository;

    public void create(CreatePotentialCustomerReq request){
        potentialCustomerRepository.save(request.toPotentialCustomer());
    }



}
