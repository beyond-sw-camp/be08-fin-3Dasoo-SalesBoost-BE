package beyond.samdasoo.potentialcustomer.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Table(name = "TB_POETNTIAL_CUSTOMER")
@Entity
public class PotentialCustomer extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_cust_no")
    private Long id; // pk

    @Column(nullable = false)
    private String name; // 이름

    private String company; // 고객사

    private String dept; // 부서

    private String position; // 직책

    private String cls; // 접촉구분

    @Enumerated(EnumType.STRING)
    private Status status; // 접촉상태

    @Enumerated(EnumType.STRING)
    private Grade grade; // 가망 등급

    private String phone; // 휴대폰 번호

    private String tel; // 유선 번호

    private String email; // 이메일

    private String fax; // 팰스번호

    private String addr; // 주소

    private String note; // 비고

    @CreationTimestamp
    @Column(name = "register_date", nullable = false,updatable = false)
    private LocalDateTime registerDate; // 잠재고객 등록일

    @Column(name = "modify_date")
    private LocalDateTime modifyDate; // 고객으로 전환일

    public PotentialCustomer() {

    }

    @Getter
    public enum Grade{
        S("S등급"),
        A("A등급"),
        B("B등급"),
        C("C등급");

        private final String message;

        Grade(String message){
            this.message = message;
        }
    }

    @Getter
    public enum Status{
        NO_CONTACT("미접촉"),
        TRY_CONTACT("접촉시도"),
        CONTACTING("접촉중"),
        NOT_CONTACT("접촉금지"),
        CONVERT_CUSTOMER("고객전환"),
        EXIST_CUSTOMER("기존고객");

        private final String message;

        Status(String message){
            this.message =  message;
        }

    }

    // 고객 전환일
    public void changeModifyDate() {
        this.modifyDate = LocalDateTime.now();
    }
    // 접촉상태 변경
    public void changeStatus(Status status){
        this.status = status;
    }

}
