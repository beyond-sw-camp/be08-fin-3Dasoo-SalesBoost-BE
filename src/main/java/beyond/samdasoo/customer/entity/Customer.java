package beyond.samdasoo.customer.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import beyond.samdasoo.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.NoSuchElementException;
import java.util.Objects;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_CUSTOMER")
@Entity
public class Customer extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_no")
    private Long id;

    @Column(nullable = false)
    private String name; // 고객 이름

    private String company; // 고객사

    private String dept; // 부서

    private String position; // 직책

    @Column(nullable = false)
    private String email; // 이메일

    @Column(nullable = false)
    private String phone; // 휴대폰번호

    private String tel; // 유선번호

    @Column(name = "grade",nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "is_keyman")
    private boolean isKeyMan; // 키맨여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no",nullable = false)
    private User user; // 담당자

    @OneToOne
    @JoinColumn(name = "p_customer_no" )
    private PotentialCustomer potentialCustomer;



    @Getter
    public enum Grade{
        S("S등급",1),
        A("A등급",2),
        B("B등급",3),
        C("C등급",4),
        D("D등급",5);

        private final String message;
        private final int code;

        Grade(String message, int code){
            this.message = message;
            this.code = code;
        }

        public static Grade getGradeByMessage(String message){
            if(message == null){
                return null;
            }
            for (Grade grade : Grade.values()) {
                if(Objects.equals(grade.getMessage(), message))
                    return grade;
            }
            throw new NoSuchElementException("유효하지 않은 등급 명 : " + message);
        }

        public static Customer.Grade getGradeByCode(int code){
            for(Customer.Grade grade : Customer.Grade.values()){
                if(grade.getCode() == code) {
                    return grade;
                }
            }
            throw new NoSuchElementException("유효하지 않은 등급 코드 : " + code);
        }
    }

    public void changeName(String name){this.name = name;}
    public void changeCompany(String company){this.company = company;}
    public void changeDept(String dept){this.dept = dept;};
    public void changePosition(String position){this.position = position;}
    public void changePhone(String phone){this.phone = phone;}
    public void changeTel(String tel){
        this.tel = tel;
    }
    public void changeEmail(String email){
        this.email = email;
    }
    public void changeGrade(String grade){
        this.grade = Grade.getGradeByMessage(grade);
    }
    public void changeKeyman(boolean isKeyMan){
        this.isKeyMan = isKeyMan;
    }

}
