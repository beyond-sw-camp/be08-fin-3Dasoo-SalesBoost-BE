package beyond.samdasoo.potentialcustomer.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PotentialCustomerListDto {

    private Long id; // pk

    private String name; // 이름

    private String company; // 고객사

    private String cls; // 접촉구분

    private int status; // 접촉상태

    private String phone; // 휴대폰 번호

    private String email; // 이메일

    private LocalDateTime createdDate;  // 생성 날짜


}
