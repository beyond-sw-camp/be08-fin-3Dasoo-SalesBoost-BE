package beyond.samdasoo.potentialcustomer.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Objects;

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

    @Column(nullable = false)
    private String cls; // 접촉구분

    @Column(name = "contact_status")
    @Enumerated(EnumType.STRING)
    private ContactStatus contactStatus; // 접촉단계

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
    private LocalDate registerDate; // 잠재고객 등록일

    @Column(name = "modify_date")
    private LocalDate modifyDate; // 고객으로 전환일

    public PotentialCustomer() {

    }

    @Getter
    public enum Grade{
        X("미선택",0),
        S("S등급",1),
        A("A등급",2),
        B("B등급",3),
        C("C등급",4);

        private final String message;
        private final int code;

        Grade(String message, int code){
            this.message = message;
            this.code = code;
        }

        public static Grade getGrade(String str){
            for (Grade grade : Grade.values()) {
                if(Objects.equals(grade.getMessage(), str))
                    return grade;
            }
            throw new NoSuchElementException("No such Grade : " + str);
        }
    }

    @Getter
    public enum ContactStatus{
        NO_CONTACT("미접촉",1),
        TRY_CONTACT("접촉시도",2),
        CONTACTING("접촉중",3),
        NOT_CONTACT("접촉금지",4),
        CONVERT_CUSTOMER("고객전환",5),
        EXIST_CUSTOMER("기존고객",6);

        private final String message;
        private final int code;

        ContactStatus(String message,int code){
            this.message =  message;
            this.code = code;
        }

        public static ContactStatus getStatus(String msg){
            for (ContactStatus status : ContactStatus.values()) {
                if(status.getMessage().equals(msg))
                    return status;
            }
            throw new IllegalArgumentException("유효하지 않은 접촉상태명 입니다 :" + msg);
        }

    }

    // 고객 전환일
    public void changeModifyDate() {
        this.modifyDate = LocalDate.now();
    }

    // 접촉상태 변경
    public void changeStatus(String status){
        this.contactStatus = ContactStatus.getStatus(status);

    }

    public void changeName(String name){
        this.name = name;
    }
    public void changeCompany(String company){
        this.company = company;
    }
    public void changeDept(String dept){
        this.dept = dept;
    }
    public void changePosition(String position){
        this.position = position;
    }

    public void changeCls(String cls){
        this.cls = cls;
    }

    public void changeGrade(String code){
        this.grade = Grade.getGrade(code);

    }

    public void changePhone(String phone){
        this.phone = phone;
    }

    public void changeTel(String tel){
        this.tel = tel;
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public void changeFax(String fax){
        this.fax = fax;
    }

    public void changeAddr(String addr){
        this.addr = addr;
    }

    public void changeNote(String note){
        this.note = note;
    }



}
