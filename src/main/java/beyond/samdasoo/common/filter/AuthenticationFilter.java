package beyond.samdasoo.common.filter;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.jwt.JwtTokenProvider;
import beyond.samdasoo.common.jwt.service.RefreshTokenService;
import beyond.samdasoo.common.response.BaseResponse;
import beyond.samdasoo.common.utils.CookieUtil;
import beyond.samdasoo.common.utils.JwtUtil;
import beyond.samdasoo.user.CustomUserDetails;
import beyond.samdasoo.user.dto.LoginUserReq;
import beyond.samdasoo.user.dto.LoginUserRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.PriorityQueue;

import static beyond.samdasoo.common.response.BaseResponseStatus.FAIL_LOGIN;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CookieUtil cookieUtil;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, CookieUtil cookieUtil, RefreshTokenService refreshTokenService) {
        super.setAuthenticationManager(authenticationManager);
        this.jwtTokenProvider = jwtTokenProvider;
        this.cookieUtil = cookieUtil;
        this.refreshTokenService = refreshTokenService;

    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 요청 body 에서 로그인 정보 추출
        try{
            LoginUserReq loginUserReq = new ObjectMapper().readValue(request.getInputStream(), LoginUserReq.class);
            String username;
            String password = loginUserReq.getPassword();

            String type = loginUserReq.getLoginType();

            if(type.equals("employeeId")){
                username = loginUserReq.getEmployeeId();
            }else{
                username = loginUserReq.getEmail();
            }

            // 인증 객체 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

            setDetails(request,authenticationToken); // 로그인 타입 여부 구분을 위해

            return this.getAuthenticationManager().authenticate(authenticationToken);

        }catch (IOException e){
            log.error(e.getMessage());
            throw new BaseException(FAIL_LOGIN);
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetails user = (CustomUserDetails) authResult.getPrincipal();
        String role = user.getUser().getRole().toString();

        String access = jwtTokenProvider.createToken(user.getEmail(),role,"ACCESS");
        String refresh = jwtTokenProvider.createToken(user.getEmail(),role,"REFRESH");

        refreshTokenService.saveToken(user.getEmail(),refresh);

        // refresh token
        Cookie cookie = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_COOKIE_NAME, refresh, JwtUtil.refreshTokenExpireDuration / 1000);
        response.addCookie(cookie);

        // 응답 객체 생성
        LoginUserRes loginUserRes = new LoginUserRes(user.getUsername(),user.getEmail(),user.getUser().getRole(),access);

        BaseResponse<LoginUserRes> responseObj = new BaseResponse<>(loginUserRes);

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = objectMapper.writeValueAsString(responseObj);

        response.getWriter().write(responseBody);


    }
}
