package beyond.samdasoo.user.controller;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.user.dto.JoinUserReq;
import beyond.samdasoo.user.dto.LoginUserReq;
import beyond.samdasoo.user.dto.UserDto;
import beyond.samdasoo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name="User APIs",description = "유저 관련 API")
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;


    /**
     * 회원가입 API
     */
    @Operation(summary = "회원가입",description = "유저 정보를 받아 회원가입을 진행한다")
    @PostMapping("/join")
    public BaseResponse<String> join(@RequestBody @Valid JoinUserReq joinUserReq){
          userService.join(joinUserReq);
          return new BaseResponse<>("회원가입을 완료했습니다");
    }

    /**
     * 로그인 API
     */
    @Operation(summary = "로그인", description = "이메일과 비밀번호를 받아 로그인을 진행한다.")
    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody @Valid LoginUserReq loginUserReq){

            userService.login(loginUserReq);

            return new BaseResponse<>("로그인을 완료했습니다");
    }

    /**
     * 유저 정보 조회 API
     */
    @Operation(summary = "유저 조회", description = "유저 정보를 조회한다.")
    @GetMapping("/{id}")
    public BaseResponse<UserDto> getUser(@PathVariable(name = "id") Long userId){

        UserDto result = userService.getUser(userId);

        return new BaseResponse<>(result);
    }



}
