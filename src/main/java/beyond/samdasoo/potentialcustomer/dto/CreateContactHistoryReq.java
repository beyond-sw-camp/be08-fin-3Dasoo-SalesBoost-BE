package beyond.samdasoo.potentialcustomer.dto;

import beyond.samdasoo.potentialcustomer.entity.ContactHistory;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import beyond.samdasoo.user.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateContactHistoryReq {

    @NotNull
    private LocalDateTime contactDate; // 접촉 날짜 (필수)

    @NotNull
    private int cls; // 접촉 구분

    private String content; // 접촉 내용

    public ContactHistory toContactHistory(User loginUser, PotentialCustomer pCustomer) {
        return ContactHistory.builder()
                .contactDate(contactDate)
                .cls(ContactHistory.CLS.getCLS(cls))
                .content(content)
                .user(loginUser)
                .pCustomer(pCustomer)
                .build();
    }
}
