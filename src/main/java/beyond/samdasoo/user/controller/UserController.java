package beyond.samdasoo.user.controller;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.common.utils.CookieUtil;
import beyond.samdasoo.common.utils.JwtUtil;
import beyond.samdasoo.user.dto.*;
import beyond.samdasoo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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
    private final CookieUtil cookieUtil;

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
    @Operation(summary = "로그인", description = "이메일/사원번호와 비밀번호를 입력해 로그인을 진행한다")
    @PostMapping("/login")
    public BaseResponse<LoginUserRes> login(@RequestBody @Valid LoginUserReq loginUserReq, HttpServletResponse response){

        TokenResult tokenResult = userService.login(loginUserReq);

        // 쿠키에 refresh token 저장
        Cookie cookie = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_COOKIE_NAME, tokenResult.getAccessToken(), JwtUtil.refreshTokenExpireDuration/1000);
       // response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        response.addCookie(cookie);

        // 로그인 응답객체 생성
        LoginUserRes result = new LoginUserRes(tokenResult.getName(),tokenResult.getEmail(),tokenResult.getRole(),tokenResult.getDept(),tokenResult.getAccessToken());

        return new BaseResponse<>(result);
    }

    /**
     * 유저 정보 조회 API
     */
    @Operation(summary = "유저 정보 조회", description = "현재 로그인한 유저 정보를 조회한다")
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

    /**
     * Access Token 재발급
     */
    @PostMapping("/reissue")
    public void reissue(HttpServletRequest request, HttpServletResponse response){
        // 1. 리프레시 토큰 쿠키를 넘겨 토큰 재발급
        Cookie refreshTokenCookie = cookieUtil.getCookie(request, JwtUtil.REFRESH_TOKEN_COOKIE_NAME);
         userService.reissue(refreshTokenCookie,request);

    }



}
