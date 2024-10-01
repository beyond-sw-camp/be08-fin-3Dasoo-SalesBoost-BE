package beyond.samdasoo.potentialcustomer.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class PotentialCustomerDto {

    private Long id; // pk

    private String name; // 이름

    private String company; // 고객사

    private String dept; // 부서

    private String position; // 직책

    private String cls; // 접촉구분

    private int status; // 접촉상태

    private int grade; // 가망 등급

    private String phone; // 휴대폰 번호

    private String tel; // 유선 번호

    private String email; // 이메일

    private String fax; // 팰스번호

    private String addr; // 주소

    private String note; // 비고

 //   private LocalDateTime registerDate; // 잠재고객 등록일

//    private LocalDateTime modifyDate; // 고객으로 전환일

    // todo : 접촉 이력
}
