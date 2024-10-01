package beyond.samdasoo.act.controller;

import beyond.samdasoo.act.dto.ActRequestDto;
import beyond.samdasoo.act.dto.ActResponseDto;
import beyond.samdasoo.act.service.ActService;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.common.response.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static beyond.samdasoo.common.response.BaseResponseStatus.ACT_NOT_EXIST;

@RestController
@RequestMapping("/api/acts")
@Tag(name="Act APIs", description = "영업활동 API")
public class ActController {
    private final ActService actService;

    @Autowired
    public ActController(ActService actService) { this.actService = actService; }


    @PostMapping
    @Operation(summary = "영업활동 등록", description = "새로운 영업활동 등록")
    public ResponseEntity<BaseResponse<ActResponseDto>> createAct(@RequestBody ActRequestDto actRequestDto) {
        ActResponseDto responseDto = actService.createAct(actRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(responseDto));
    }

    @GetMapping("/{no}")
    @Operation(summary = "영업활동 조회", description = "특정 영업활동 조회")
    public ResponseEntity<BaseResponse<ActResponseDto>> getActById(@PathVariable("no") Long no) {
        try {
            ActResponseDto responseDto = actService.getActById(no);
            return ResponseEntity.ok(new BaseResponse<>(responseDto));
        } catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }

    @PatchMapping("/{no}")
    @Operation(summary = "영업활동 수정", description = "특정 영업활동 수정")
    public ResponseEntity<BaseResponse<String>> patchUpdateAct(@PathVariable("no") Long no, @RequestBody ActRequestDto actUpdateDto) {
        try {
            actService.updateAct(no, actUpdateDto);
            return ResponseEntity.ok(new BaseResponse<>("영업활동을 수정하였습니다"));
        } catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }

    @DeleteMapping("/{no}")
    @Operation(summary = "영업활동 삭제", description = "특정 영업활동 삭제")
    public ResponseEntity<BaseResponse<String>> deleteAct(@PathVariable("no") Long no) {
        try {
            actService.deleteAct(no);
            return ResponseEntity.ok(new BaseResponse<>("영업활동 삭제에 성공했습니다."));
        } catch (BaseException ex) {
            BaseResponseStatus status = ex.getStatus();
            return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
        }
    }

}