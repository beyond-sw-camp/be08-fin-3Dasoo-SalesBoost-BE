package beyond.samdasoo.user.dto;

import beyond.samdasoo.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilterUserDto {
    private Long userNo;
    private String name;

    public FilterUserDto(User user) {
        this.userNo = user.getId();
        this.name = user.getName();
    }
}
