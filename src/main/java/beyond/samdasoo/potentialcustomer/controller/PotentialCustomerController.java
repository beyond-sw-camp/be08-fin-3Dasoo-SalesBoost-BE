package beyond.samdasoo.potentialcustomer.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.potentialcustomer.dto.CreatePotentialCustomerReq;
import beyond.samdasoo.potentialcustomer.dto.PotentialCustomerDto;
import beyond.samdasoo.potentialcustomer.dto.PotentialCustomerListDto;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import beyond.samdasoo.potentialcustomer.service.PotentialCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/sales/prospect")
@RestController
public class PotentialCustomerController {

    private final PotentialCustomerService potentialCustomerService;

    /**
     잠재고객 생성 API
     */
    @PostMapping("")
    public BaseResponse<String> createPotentialCustomer(@RequestBody @Valid CreatePotentialCustomerReq request) {
        potentialCustomerService.create(request);
        return new BaseResponse<>("잠재고객 생성을 완료하였습니다.");

    }


    /**
     잠재고객 수정 API
     */
    @PatchMapping("/{prospectId}")
    public void updatePotentialCustomer(@PathVariable String prospectId) {


    }

    /**
     * 특정 잠재고객 정보 조회 API
     */
    @GetMapping("/{no}")
    public BaseResponse<PotentialCustomerDto> getPotentialCustomer(@PathVariable Long no) {
        PotentialCustomerDto result = potentialCustomerService.getPotentialCustomer(no);
        System.out.println(result);
        return new BaseResponse<>(result);
    }


    /**
     *  잠재고객 목록 조회 API
     */
    @GetMapping
    public BaseResponse<List<PotentialCustomerListDto>> getAllPotentialCustomer() {
        List<PotentialCustomerListDto> result = potentialCustomerService.getAllPotentialCustomer();
        return new BaseResponse<>(result);
    }


}
