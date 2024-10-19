package beyond.samdasoo.lead.dto;

import beyond.samdasoo.lead.Entity.AwarePath;
import beyond.samdasoo.lead.Entity.Lead;
import beyond.samdasoo.lead.Entity.LeadStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LeadResponseDto {
    private Long leadNo;       // 리드 번호
    private String name;       // 리드 이름
    private LeadStatus status; // 리드 상태
    private int expSales;      // 예상 매출
    private int expMargin;     // 예상 이익률
    private int expProfit;     // 예상 이익금
    private Long process;       // 프로세스
    private Long subProcess;    // 서브 프로세스
    private int successPer;    // 성공 확률
    private LocalDate startDate; // 시작일
    private LocalDate endDate;   // 종료일
    private AwarePath awarePath; // 인지 경로
    private String note;       // 메모
    private Long customerNo;   // 고객 번호 (FK)
    private String customerName; // 고객 이름
    private String userName;  // 담당자 이름
    private List<StepResponseDto> steps; // 영업단계

    public LeadResponseDto(Lead lead) {
        this.leadNo = lead.getNo();
        this.name = lead.getName();
        this.status = lead.getStatus();
        this.expSales = lead.getExpSales();
        this.expMargin = lead.getExpMargin();
        this.expProfit = lead.getExpProfit();
        this.process = lead.getProcess();
        this.subProcess = lead.getSubProcess();
        this.successPer = lead.getSuccessPer();
        this.startDate = lead.getStartDate();
        this.endDate = lead.getEndDate();
        this.awarePath = lead.getAwarePath();
        this.note = lead.getNote();
        this.customerNo = lead.getCustomer().getId();
        this.customerName = lead.getCustomer().getName();
        this.userName = lead.getCustomer().getUser().getName();
        if (lead.getSteps() != null) {
            this.steps = lead.getSteps().stream()
                    .map(StepResponseDto::new)
                    .collect(Collectors.toList());
        }
    }
}
