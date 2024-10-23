package beyond.samdasoo.customer.controller;

import beyond.samdasoo.common.dto.SearchCond;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.customer.dto.*;
import beyond.samdasoo.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer APIs", description = "고객 API")
@RequiredArgsConstructor
@RequestMapping("/api/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    /*
    고객 등록 API
     */
    @Operation(summary = "고객 등록", description = "새로운 고객 등록")
    @PostMapping("/add")
    public BaseResponse<String> createCustomer(@RequestBody CustomerCreateReq req) {
        customerService.create(req);

        return new BaseResponse<>("고객 등록을 완료했습니다");

    }

    /*
     고객 조회 API
     */
    @GetMapping("/{id}")
    public BaseResponse<CustomerGetRes> getCustomer(@PathVariable("id") Long id) {
        CustomerGetRes result = customerService.getCustomer(id);
        return new BaseResponse<>(result);

    }


    /*
    고객 수정 API
     */
    @PatchMapping("/{customerId}")
    public BaseResponse<String> patchCustomer(@PathVariable Long customerId, @RequestBody UpdateCustomerReq request){
        customerService.updateCustomer(customerId,request);
        return new BaseResponse<>("고객 정보가 수정되었습니다");
    }

    /*
    고객 삭제 API
     */
    @DeleteMapping
    public void deleteCustomer() {
    }

    /*
    고객 목록 조회 API
    */
    @GetMapping
    public BaseResponse<List<CustomersGetRes>> getCustomers(){
        List<CustomersGetRes> result = customerService.getLists();
        return new BaseResponse<>(result);
    }

    /*
    고객 목록 조회 by Filter API
     */
    @PostMapping
    public BaseResponse<List<CustomersGetRes>> getCustomersByFilter(@RequestBody SearchCriteriaDTO searchCriteria){

        List<CustomersGetRes> result = customerService.getListByFilter(searchCriteria);
        return new BaseResponse<>(result);
    }

    @PostMapping("/status/main")
    @Operation(summary = "고객 필터 조회", description = "검색조건(생성일, 담당자)에 맞는 고객을 조회한다.")
    public BaseResponse<Long> getCustomerCount(@RequestBody SearchCond searchCond) {
        Long result = customerService.getCustomerCount(searchCond);
        return new BaseResponse<>(result);
    }
}
