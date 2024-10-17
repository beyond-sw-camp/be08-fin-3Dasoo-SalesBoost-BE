package beyond.samdasoo.lead.controller;

import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.lead.dto.LeadRequestDto;
import beyond.samdasoo.lead.dto.LeadResponseDto;
import beyond.samdasoo.lead.dto.LeadSearchCond;
import beyond.samdasoo.lead.service.LeadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leads")
@Tag(name = "Lead APIs", description = "영업기회 API")
public class LeadController {
    private final LeadService leadService;

    @PostMapping
    @Operation(summary = "영업기회 등록", description = "신규 영업기회 등록")
//    @SecurityRequirement(name = "bearerAuth")
    public BaseResponse<LeadResponseDto> createLead(@RequestBody LeadRequestDto leadRequestDto) {
        LeadResponseDto createdLead = leadService.createLead(leadRequestDto);
        return new BaseResponse<>(createdLead);
    }

    @GetMapping
    @Operation(summary = "전체 영업기회 조회", description = "모든 영업기회 조회")
    public BaseResponse<List<LeadResponseDto>> getAllLeads() {
        List<LeadResponseDto> leads = leadService.getAllLeads();
        return new BaseResponse<>(leads);
    }

    @PostMapping("/filter")
    @Operation(summary = "검색조건 영업기회 조회", description = "검색조건에 따른 영업기회 조회")
    public BaseResponse<List<LeadResponseDto>> getLeadsByDto(@RequestBody LeadSearchCond searchCond) {
        List<LeadResponseDto> leads = leadService.getFilteredLeads(searchCond);
        return new BaseResponse<>(leads);
    }

    @GetMapping("/{no}")
    @Operation(summary = "영업기회 상세 조회", description = "특정 영업기회 조회")
    public BaseResponse<LeadResponseDto> getLeadById(@PathVariable Long no) {
        LeadResponseDto lead = leadService.getLeadById(no);
        return new BaseResponse<>(lead);
    }

    @PatchMapping("/{no}")
    @Operation(summary = "영업기회 수정", description = "특정 영업기회 수정")
    public BaseResponse<String> updateLead(@PathVariable Long no, @RequestBody LeadRequestDto leadRequestDto) {
        leadService.updateLead(no, leadRequestDto);
        return new BaseResponse<>(no + " 영업기회가 수정됐습니다.");
    }

    @DeleteMapping("/{no}")
    @Operation(summary = "영업기회 삭제", description = "특정 영업기회 삭제")
    public BaseResponse<String> deleteLead(@PathVariable Long no) {
        leadService.deleteLead(no);
        return new BaseResponse<>(no + " 영업기회가 삭제됐습니다.");
    }
}
