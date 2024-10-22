package beyond.samdasoo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class UserDepartmentDto {
    private Long userId; // 유저 pk
    private String userName; // 유저명
    private String userDeptName; // 유저 부서명

    public UserDepartmentDto(Long userId,String userName,String  userDeptName){
        this.userId = userId;
        this.userName = userName;
        this.userDeptName = userDeptName;
    }

}
