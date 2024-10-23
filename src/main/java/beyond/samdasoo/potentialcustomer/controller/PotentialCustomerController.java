package beyond.samdasoo.potentialcustomer.controller;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.potentialcustomer.dto.SearchCriteriaDTO;
import beyond.samdasoo.potentialcustomer.dto.*;
import beyond.samdasoo.potentialcustomer.service.PotentialCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static beyond.samdasoo.common.response.BaseResponseStatus.*;
import static beyond.samdasoo.common.response.BaseResponseStatus.NAME_EMPTY;

@RequiredArgsConstructor
@RequestMapping("/api/pcustomers")
@Tag(name="PotentialCustomer APIs", description = "잠재고객 API")
@RestController
public class PotentialCustomerController {

    private final PotentialCustomerService potentialCustomerService;

    /**
     잠재고객 생성 API
     */
    @PostMapping("/add")
    @Operation(summary = "잠재고객 등록", description = "새로운 잠재고객을 등록한다")
    public BaseResponse<String> createPotentialCustomer(@RequestBody CreatePotentialCustomerReq request) {
        validateInputEmptyCreate(request);
        potentialCustomerService.create(request);
        return new BaseResponse<>("잠재고객 생성을 완료하였습니다.");

    }


    /**
     잠재고객 정보 수정 API
     */
    @PatchMapping("/{prospectId}")
    @Operation(summary = "잠재고객 수정", description = "잠재고객 정보를 수정한다")
    public BaseResponse<String> updatePotentialCustomer(@PathVariable Long prospectId,@RequestBody UpdatePotentialCustomerReq request) {
        potentialCustomerService.updatePotentialCustomer(prospectId, request);
        return new BaseResponse<>("고객 정보가 수정되었습니다");
    }

    /**
     * 특정 잠재고객 정보 조회 API
     */
    @GetMapping("/{prospectId}")
    @Operation(summary = "잠재고객 조회", description = "특정 잠재고객 정보를 조회한다")
    public BaseResponse<PotentialCustomerDto> getPotentialCustomer(@PathVariable Long prospectId) {
        PotentialCustomerDto result = potentialCustomerService.getPotentialCustomer(prospectId);
        System.out.println(result);
        return new BaseResponse<>(result);
    }


    /**
     *  잠재고객 목록 조회 API
     */
    @GetMapping
    @Operation(summary = "잠재고객 목록 조회", description = "잠재고객 목록을 조회한다")
    public BaseResponse<List<PotentialCustomersGetRes>> getPotentialCustomers() {
        List<PotentialCustomersGetRes> result = potentialCustomerService.getAllPotentialCustomer();
        return new BaseResponse<>(result);
    }

    /**
     * 잠재고객 목록 조회 by Filter API
     */
    @PostMapping
    public BaseResponse<List<PotentialCustomersGetRes>> getPotentialCustomersByFilter(@RequestBody SearchCriteriaDTO searchCriteria){
        List<PotentialCustomersGetRes> result  = potentialCustomerService.getListByFilter(searchCriteria);
        return new BaseResponse<>(result);
    }

    /**
        접촉 이력 생성 API
     */
    @PostMapping("/{prospectId}/history")
    @Operation(summary = "접촉이력 등록", description = "잠재고객에 대한 접촉이력을 등록한다")
    public BaseResponse<String> insertContactHistory(@PathVariable Long prospectId,@RequestBody @Valid CreateContactHistoryReq request){
        potentialCustomerService.insertContactHistory(prospectId,request);
        return new BaseResponse<>("새 이력을 등록했습니다");
    }

    /**
        접촉 이력 목록 조회 API -> 목록 조회시 pk 값도 응답값에 추가
     */
    @GetMapping("{prospectId}/history")
    @Operation(summary = "접촉이력 목록 조회", description = "특정 잠재고객에 대한 접촉이력 목록을 조회한다")
    public BaseResponse<List<ContactHistoryDto>> getContactHistoryList(@PathVariable Long prospectId){
        List<ContactHistoryDto> result = potentialCustomerService.getContactHistoryList(prospectId);

        return new BaseResponse<>(result);

    }

    /**
        접촉 이력 삭제 API
     */
    @DeleteMapping("/history/{historyId}")
    @Operation(summary = "접촉이력 삭제", description = "특정 접촉 이력을 삭제한다")
    public BaseResponse<String> deleteContactHistory(@PathVariable Long historyId) {

        potentialCustomerService.deleteContactHistory(historyId);

        return new BaseResponse<>("해당 내용을 삭제했습니다.");
    }

    private void validateInputEmptyCreate(CreatePotentialCustomerReq req) {
        if (req.getName().trim().isEmpty()||req.getName()==null) { // 이름
            throw new BaseException(NAME_EMPTY);
        }
        if (req.getCls().isEmpty()|| req.getCls()==null) { // 접촉구분
            throw new BaseException(PC_CLS_EMPTY);
        }
        if(req.getContactStatus().isEmpty()||req.getContactStatus()==null){ // 접촉상태
            throw new BaseException(PC_STATUS_EMPTY);
        }
        if(req.getPhone().isEmpty()||req.getPhone()==null){ // 핸드폰
            throw new BaseException(PC_PHONE_EMPTY);
        }

    }

}
