package beyond.samdasoo.common.filter;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.user.dto.LoginUserReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import static beyond.samdasoo.common.response.BaseResponseStatus.FAIL_LOGIN;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

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
            throw new BaseException(FAIL_LOGIN);
        }


    }
}
