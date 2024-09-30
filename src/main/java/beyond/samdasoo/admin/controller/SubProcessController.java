package beyond.samdasoo.admin.controller;

import beyond.samdasoo.admin.dto.SubProcessRequestDto;
import beyond.samdasoo.admin.dto.SubProcessResponseDto;
import beyond.samdasoo.admin.entity.SubProcess;
import beyond.samdasoo.admin.service.SubProcessService;
import beyond.samdasoo.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subprocesses")
@Tag(name="SubProcess APIs",description = "하위 프로세스 관련 API")
public class SubProcessController {

    @Autowired
    private SubProcessService subProcessService;

    @GetMapping("/{processName}")
    @Operation(summary = "해당 상위 프로세스의 하위 프로세스 조회", description = "관리자 계정에서 하위 프로세스를 조회")
    public List<SubProcessResponseDto> getSubProcessByProcessNo(@PathVariable("processName") String processName){
        return subProcessService.getSubProcessesByProcessName(processName);
    }

    @PostMapping
    @Operation(summary = "하위 프로세스 추가", description = "관리자 계정에 하위 프로세스를 추가")
    public BaseResponse<String> addSubProcess(@RequestBody SubProcessRequestDto request){
        subProcessService.addSubProcess(request);

        return new BaseResponse<>("하위 프로세스 등록을 완료했습니다.");
    }
}
