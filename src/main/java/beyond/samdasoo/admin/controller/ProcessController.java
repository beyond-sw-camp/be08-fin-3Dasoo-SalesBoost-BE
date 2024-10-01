package beyond.samdasoo.admin.controller;

import beyond.samdasoo.admin.dto.ProcessRequestDto;
import beyond.samdasoo.admin.dto.ProcessResponseDto;
import beyond.samdasoo.admin.service.ProcessService;
import beyond.samdasoo.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Process APIs",description = "프로세스 관련 API")
@RestController
@RequestMapping("/api/processes")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    // 모든 상품 조회 API
    @GetMapping
    @Operation(summary = "모든 프로세스 조회", description = "관리자 계정에 등록되어있는 모든 프로세스를 조회")
    public BaseResponse<List< ProcessResponseDto >> getAllProducts() {
        List<ProcessResponseDto> processes = processService.getAllProcesses();
        return new BaseResponse<>(processes);
    }

    @PostMapping
    @Operation(summary = "프로세스 추가", description = "관리자 계정에 프로세스를 추가")
    public BaseResponse<String> addProcess(@RequestBody ProcessRequestDto request){
        processService.addProcess(request);
        return new BaseResponse<>("프로세스 등록을 완료했습니다.");
    }

    @DeleteMapping("/{no}")
    @Operation(summary = "프로세스 삭제", description = "관리자 계정에 프로세스 번호로 프로세스 삭제")
    public BaseResponse<String> deleteProcessByNo(@PathVariable("no") Long no){

        processService.deleteProcessByNo(no);

        return new BaseResponse<>("프로세스 삭제를 완료했습니다.");
    }

    @PatchMapping("/{no}")
    @Operation(summary = "프로세스 수정", description = "관리자 계정에 프로세스 번호로 프로세스 수정")
    public BaseResponse<String> updateProcessByNo(@PathVariable("no") Long no, @RequestBody ProcessRequestDto request){

        processService.updateProcessByNo(no, request);

        return new BaseResponse<>("프로세스 수정을 완료했습니다.");
    }

}
