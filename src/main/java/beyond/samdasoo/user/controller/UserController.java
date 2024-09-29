package beyond.samdasoo.user.controller;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.user.dto.JoinUserReq;
import beyond.samdasoo.user.dto.LoginUserReq;
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
    public BaseResponse<String> join(@RequestBody @Valid JoinUserReq joinUserReq){
          userService.join(joinUserReq);
          return new BaseResponse<>("회원가입을 완료했습니다");
    }

    /**
     * 로그인 API
     */
    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody @Valid LoginUserReq loginUserReq){

            userService.login(loginUserReq);

            return new BaseResponse<>("로그인을 완료했습니다");
    }




}
