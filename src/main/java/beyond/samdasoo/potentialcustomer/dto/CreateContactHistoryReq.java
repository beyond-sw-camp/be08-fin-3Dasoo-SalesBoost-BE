package beyond.samdasoo.potentialcustomer.dto;

import beyond.samdasoo.potentialcustomer.entity.ContactHistory;
import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import beyond.samdasoo.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CreateContactHistoryReq {

    @NotNull
    @Schema(description = "접촉날짜", defaultValue = "2024-12-11")
    private LocalDate contactDate;

    @NotNull
    @Schema(description = "접촉구분", defaultValue = "5")
    private int cls;

    @Schema(description = "접촉내용", defaultValue = "채널톡으로 오후 2시 미팅")
    private String content;

    public ContactHistory toContactHistory(User loginUser, PotentialCustomer pCustomer) {
        return ContactHistory.builder()
                .contactDate(contactDate)
                .cls(ContactHistory.CLS.getCLS(cls))
                .content(content)
                .user(loginUser)
                .pcustomer(pCustomer)
                .build();
    }
}
