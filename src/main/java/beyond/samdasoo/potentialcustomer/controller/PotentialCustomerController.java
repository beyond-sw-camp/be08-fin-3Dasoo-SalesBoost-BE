package beyond.samdasoo.potentialcustomer.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.potentialcustomer.dto.*;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import beyond.samdasoo.potentialcustomer.service.PotentialCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/sales/prospect")
@Tag(name="PotentialCustomer APIs", description = "잠재고객 API")
@RestController
public class PotentialCustomerController {

    private final PotentialCustomerService potentialCustomerService;

    /**
     잠재고객 생성 API
     */
    @PostMapping("")
    @Operation(summary = "잠재고객 등록", description = "새로운 잠재고객을 등록한다")
    public BaseResponse<String> createPotentialCustomer(@RequestBody @Valid CreatePotentialCustomerReq request) {
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
        return new BaseResponse<>("수정을 완료했습니다.");
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
    public BaseResponse<List<PotentialCustomerListDto>> getAllPotentialCustomer() {
        List<PotentialCustomerListDto> result = potentialCustomerService.getAllPotentialCustomer();
        return new BaseResponse<>(result);
    }

    /**
        접촉 이력 생성 API
     */
    @PostMapping("/{prospectId}/history")
    @Operation(summary = "접촉이력 등록", description = "잠재고객에 대한 접촉이력을 등록한다")
    public BaseResponse<String> insertContactHistory(@PathVariable Long prospectId,@RequestBody @Valid CreateContactHistoryReq request){
        potentialCustomerService.insertContactHistory(prospectId,request);
        return new BaseResponse<>("접촉 이력을 생성했습니다.");
    }

    /**
        접촉 이력 목록 조회 API -> 목록 조회시 pk 값도 응답값에 추가
     */
    @GetMapping("{prospectId}/history")
    @Operation(summary = "점촉이력 목록 조회", description = "특정 잠재고객에 대한 접촉이력 목록을 조회한다")
    public BaseResponse<String> getContactHistoryList(@PathVariable Long prospectId){
        potentialCustomerService.getContactHistoryList(prospectId);

        return new BaseResponse<>("good");

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

}