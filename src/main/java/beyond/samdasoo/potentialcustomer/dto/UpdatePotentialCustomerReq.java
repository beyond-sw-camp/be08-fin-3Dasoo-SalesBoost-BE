package beyond.samdasoo.potentialcustomer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdatePotentialCustomerReq {

    @Schema(description = "고객 이름", defaultValue = "주단태22")
    @NotNull
    private String name; // 필수

    @Schema(description = "고객사", defaultValue = "카카오")
    private String company; // 고객사

    @Schema(description = "부서명", defaultValue = "개발부22")
    private String dept; // 부서

    @Schema(description = "직책", defaultValue = "사장")
    private String position; // 직책

    @Schema(description = "접촉구분", defaultValue = "지인소개")
    @NotNull
    private String cls; // 접촉구분

    @Schema(description = "접촉상태", defaultValue = "2")
    @NotNull
    private int status; // 접촉상태

    @Schema(description = "가망등급", defaultValue = "3")
    private int grade; // 가망 등급

    @Schema(description = "휴대폰 번호", defaultValue = "010-1234-5678")
    private String phone;

    @Schema(description = "유선번호", defaultValue = "032-136-0987")
    private String tel;

    @Schema(description = "이메일", defaultValue = "judan@gmail.com")
    private String email;

    @Schema(description = "펙스번호", defaultValue = "070-1111-3333")
    private String fax;

    @Schema(description = "주소", defaultValue = "(08390) 서울특별시 구로구 디지털로26길")
    private String addr;

    @Schema(description = "비고", defaultValue = "아무거나 쓰는칸")
    private String note; // 비고
}
