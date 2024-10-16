package beyond.samdasoo.lead.dto;

import beyond.samdasoo.lead.Entity.AwarePath;
import beyond.samdasoo.lead.Entity.LeadStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeadRequestDto {
    private String name;      // 리드 이름
    private LeadStatus status;  // 리드 상태
    private int expSales;     // 예상 매출
    private int expMargin;    // 예상 이익률
    private int expProfit;    // 예상 이익금
    private int process;      // 프로세스
    private int subProcess;   // 서브 프로세스
    private int successPer;   // 성공 확률
    private LocalDate startDate;  // 시작일
    private LocalDate endDate;    // 종료일
    private AwarePath awarePath;  // 인지 경로
    private String note;      // 메모
    private Long custNo;  // 고객 정보 (FK)
}
