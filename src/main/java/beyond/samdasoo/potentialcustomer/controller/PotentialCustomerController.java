package beyond.samdasoo.potentialcustomer.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.potentialcustomer.dto.*;
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
     잠재고객 정보 수정 API
     */
    @PatchMapping("/{prospectId}")
    public BaseResponse<PotentialCustomerDto> updatePotentialCustomer(@PathVariable Long prospectId,@RequestBody UpdatePotentialCustomerReq request) {
        PotentialCustomerDto result = potentialCustomerService.updatePotentialCustomer(prospectId, request);
        return new BaseResponse<>(result);
    }

    /**
     * 특정 잠재고객 정보 조회 API
     */
    @GetMapping("/{prospectId}")
    public BaseResponse<PotentialCustomerDto> getPotentialCustomer(@PathVariable Long prospectId) {
        PotentialCustomerDto result = potentialCustomerService.getPotentialCustomer(prospectId);
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

    /**
        접촉 이력 생성 API
     */
    @PostMapping("/{prospectId}/history")
    public BaseResponse<String> insertContactHistory(@PathVariable Long prospectId,@RequestBody @Valid CreateContactHistoryReq request){
        potentialCustomerService.insertContactHistory(prospectId,request);
        return new BaseResponse<>("접촉 이력을 생성했습니다.");
    }

    /**
        접촉 이력 목록 조회 API -> 목록 조회시 pk 값도 응답값에 추가
     */

    /**
        접촉 이력 삭제 API
     */
    @DeleteMapping("/history/{historyId}")
    public BaseResponse<String> deleteContactHistory(@PathVariable Long historyId) {

        potentialCustomerService.deleteContactHistory(historyId);

        return new BaseResponse<>("해당 내용을 삭제했습니다.");
    }

}
