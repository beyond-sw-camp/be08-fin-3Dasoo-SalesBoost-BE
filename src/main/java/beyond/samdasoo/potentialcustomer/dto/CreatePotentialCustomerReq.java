package beyond.samdasoo.potentialcustomer.dto;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class CreatePotentialCustomerReq {

    @Schema(description = "고객 이름", defaultValue = "주단태")
    @NotNull
    private String name; // 필수

    @Schema(description = "고객사", defaultValue = "네이버")
    private String company;

    @Schema(description = "부서명", defaultValue = "개발부")
    private String dept;

    @Schema(description = "직책", defaultValue = "팀장")
    private String position;

    @Schema(description = "접촉구분", defaultValue = "지인소개")
    @NotNull
    private String cls;

    @Schema(description = "접촉상태", defaultValue = "접촉중")
    @NotNull
    private String contactStatus;

    @Schema(description = "가망등급", defaultValue = "3")
    private String grade;

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


    public PotentialCustomer toPotentialCustomer() {
        return PotentialCustomer.builder()
                .name(name)
                .company(company)
                .dept(dept)
                .position(position)
                .cls(cls)
                .contactStatus(PotentialCustomer.ContactStatus.getStatus(contactStatus))
                .grade(PotentialCustomer.Grade.getGrade(grade))
                .phone(phone)
                .tel(tel)
                .email(email)
                .fax(fax)
                .addr(addr)
                .note(note)
                .build();
    }
}
