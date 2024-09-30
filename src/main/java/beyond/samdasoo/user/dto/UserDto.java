package beyond.samdasoo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private String email;
    private String name;
    private UserRole role;

//    public LoginUserRes toLoginRes(String token){
//        return LoginUserRes
//                .builder()
//                .accessToken(token)
//                .email(email)
//                .name(name)
//                .role(role)
//                .build();
//    }
}
