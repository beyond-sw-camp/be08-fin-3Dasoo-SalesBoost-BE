package beyond.samdasoo.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateCustomerReq {

    @Schema(description = "고객 이름", defaultValue = "이몽룡")
    @NotNull
    private String name; // 필수

    @Schema(description = "고객사", defaultValue = "LG")
    private String company; // 고객사

    @Schema(description = "부서명", defaultValue = "영업부")
    private String dept;

    @Schema(description = "직책", defaultValue = "사원")
    private String position;

    @Schema(description = "휴대폰 번호", defaultValue = "010-1234-5678")
    private String phone;

    @Schema(description = "유선번호", defaultValue = "032-136-0987")
    private String tel;

    @Schema(description = "이메일", defaultValue = "hello@gmail.com")
    private String email;

    @Schema(description = "등급", defaultValue = "S등급")
    @NotNull
    private String grade; // 등급

    @Schema(description = "키맨 여부",defaultValue = "false")
    @JsonProperty("isKeyMan")
    private boolean isKeyMan;
}
