package beyond.samdasoo.potentialcustomer.dto;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import lombok.Getter;

@Getter
public class CreatePotentialCustomerReq {
    private String name; // 필수

    private String company; // 고객사

    private String dept; // 부서

    private String position; // 직책

    private String cls; // 접촉구분

    private PotentialCustomer.Status status; // 접촉상태

    private PotentialCustomer.Grade grade; // 가망 등급

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
                .status(status)
                .grade(grade)
                .phone(phone)
                .tel(tel)
                .email(email)
                .fax(fax)
                .addr(addr)
                .note(note)
                .build();
    }
}
