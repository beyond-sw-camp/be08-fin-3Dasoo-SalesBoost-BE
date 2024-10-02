package beyond.samdasoo.potentialcustomer.entity;

import beyond.samdasoo.common.entity.BaseEntity;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Table(name = "TB_CONTACT_HISTORY")
@Entity
public class ContactHistory extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_history_no")
    private Long id;

    @Column(name = "contact_date",nullable = false)
    private LocalDateTime contactDate; // 접촉 날짜

    @Enumerated(EnumType.STRING)
    private CLS cls; // 접촉 구분

    @Column(nullable = true)
    private String content; // 접촉 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_no",nullable = true)
    private User user;   // 해당 접촉 이력을 작성한 영업사원 (로그인된 유저)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_cust_no",nullable = false)
    private PotentialCustomer pCustomer;  // 접촉이력 주인 (잠재고객)

    @Getter
    public enum CLS{

        CALL("전화",1),
        EMAIL("메일",2),
        VISIT("방문",3),
        ONLINE_MEET("온라인 미팅",4),
        CHANNEL_TALK("채널톡",5),
        ETC("기타",6);

        private final String message;
        private final int code;

        CLS(String message, int code) {
            this.message = message;
            this.code = code;
        }
        
        public static CLS getCLS(int code){
            for(CLS cls: CLS.values()){
                if(cls.getCode() == code){
                    return cls;
                }
            }
            throw new NoSuchElementException("No such CLS with code " + code);
        }

    }
}
