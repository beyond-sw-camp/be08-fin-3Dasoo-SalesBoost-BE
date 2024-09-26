package beyond.samdasoo.user.controller;
import beyond.samdasoo.user.dto.JoinUserReq;
import beyond.samdasoo.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;


    /**
     * 회원가입 API
     */
    @PostMapping("/join")
    public String join(@RequestBody @Valid JoinUserReq joinUserReq){
          userService.join(joinUserReq);
          return "회원가입 완료";
    }

    /**
     * 로그인 API
     */
}
