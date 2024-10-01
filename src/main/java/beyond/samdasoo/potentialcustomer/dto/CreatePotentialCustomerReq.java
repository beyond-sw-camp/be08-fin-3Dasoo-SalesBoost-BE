package beyond.samdasoo.potentialcustomer.dto;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreatePotentialCustomerReq {
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


    public PotentialCustomer toPotentialCustomer() {
        return PotentialCustomer.builder()
                .name(name)
                .company(company)
                .dept(dept)
                .position(position)
                .cls(cls)
                .status(PotentialCustomer.Status.getStatus(status))
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
