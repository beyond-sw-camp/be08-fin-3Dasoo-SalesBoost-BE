package beyond.samdasoo.potentialcustomer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdatePotentialCustomerReq {

    @NotNull
    private String name; // 필수

    private String company; // 고객사

    private String dept; // 부서

    private String position; // 직책

    @NotNull
    private String cls; // 접촉구분

    @NotNull
    private int status; // 접촉상태

    private int grade; // 가망 등급

    private String phone; // 휴대폰 번호

    private String tel; // 유선 번호

    private String email; // 이메일

    private String fax; // 팰스번호

    private String addr; // 주소

    private String note; // 비고
}
