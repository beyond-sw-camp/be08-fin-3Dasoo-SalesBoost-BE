package beyond.samdasoo.user.controller;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.user.dto.*;
import beyond.samdasoo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static beyond.samdasoo.common.utils.UserUtil.getLoginUserEmail;

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
    public BaseResponse<JoinUserRes> join(@RequestBody @Valid JoinUserReq joinUserReq){
        JoinUserRes result = userService.join(joinUserReq);
        return new BaseResponse<>(result);
    }

    /**
     * 로그인 API
     */
    @Operation(summary = "로그인", description = "이메일과 비밀번호를 받아 로그인을 진행한다.")
    @PostMapping("/login")
    public BaseResponse<LoginUserRes> login(@RequestBody @Valid LoginUserReq loginUserReq){

            LoginUserRes result = userService.login(loginUserReq);


            // todo : 리프레시 토큰 발급

            return new BaseResponse<>(result);
    }

    /**
     * 유저 정보 조회 API
     */
    @Operation(summary = "유저 정보 조회", description = "현재 로그인한 유저 정보를 조회한다.")
    @GetMapping("/my-info")
    public BaseResponse<UserDto> getUser(){
        String loginUserEmail = getLoginUserEmail();
        UserDto result = userService.getUser(loginUserEmail);

        return new BaseResponse<>(result);
    }

    /**
     * 유저 정보 수정 API
     */
//    @PatchMapping("/my-info")
//    public BaseResponse<String> updateUserInfo(){
//        String loginUserEmail = getLoginUserEmail();
//        userService.updateUserInfo(loginUserEmail);
//        return new BaseResponse<>("유저 정보 수정 성공");
//    }



}
