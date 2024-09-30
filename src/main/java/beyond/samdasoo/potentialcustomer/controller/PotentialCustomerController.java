package beyond.samdasoo.potentialcustomer.controller;

import beyond.samdasoo.potentialcustomer.dto.CreatePotentialCustomerReq;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import beyond.samdasoo.potentialcustomer.service.PotentialCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/sales")
@RestController
public class PotentialCustomerController {

    private final PotentialCustomerService potentialCustomerService;

    /**
     잠재고객 생성 API
     */
    @PostMapping("")
    public String createPotentialCustomer(@RequestBody CreatePotentialCustomerReq request) {
        potentialCustomerService.create(request);
        return "잠재고객 생성";

    }


    /**
     잠재고객 수정 API
     */
    @PatchMapping("/{prospectId}")
    public void updatePotentialCustomer(@PathVariable String prospectId) {


    }

}
