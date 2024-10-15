package beyond.samdasoo.customer.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.customer.dto.CustomerCreateReq;
import beyond.samdasoo.customer.dto.CustomerGetRes;
import beyond.samdasoo.customer.dto.CustomersGetRes;
import beyond.samdasoo.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Customer APIs", description = "고객 API")
@RequiredArgsConstructor
@RequestMapping("/api/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    /*
    고객 등록 API
     */
    @Operation(summary = "고객 등록", description = "새로운 고객 등록")
    @PostMapping("")
    public BaseResponse<String> createCustomer(@RequestBody CustomerCreateReq req){
        customerService.create(req);

        return new BaseResponse<>("고객 등록을 완료했습니다");

    }
    /*
     고객 조회 API
     */
    @GetMapping("/{id}")
    public BaseResponse<CustomerGetRes> getCustomer(@PathVariable("id") Long id){
        CustomerGetRes result= customerService.getCustomer(id);
        return new BaseResponse<>(result);

    }



    /*
    고객 수정 API
     */
    @PatchMapping()
    public void patchCustomer(){


    }

    /*
    고객 삭제 API
     */
    @DeleteMapping
    public void deleteCustomer(){}


    /*
    고객 목록 조회 API
     */
    @GetMapping
    public BaseResponse<List<CustomersGetRes>> getCustomers(){
        List<CustomersGetRes> result = customerService.getList();
        return new BaseResponse<>(result);
    }
}
