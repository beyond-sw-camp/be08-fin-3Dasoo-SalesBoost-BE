package beyond.samdasoo.common.jwt;

import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.user.dto.UserRole;
import beyond.samdasoo.user.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // JWT 검증 필터 -> 헤더로 들어온 jwt 토큰을 검증

    private static String[] whiteList={"/api/users/login","/api/users/join"};
    private final JwtTokenProvider jwtTokenProvider;
    String jwtToken;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        log.info("requestURI: {}", requestURI);

        if(!isAuthenticatedPath(requestURI)){
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION); // null
        log.info("authorizationHeader: {}", authorizationHeader);
        if(authorizationHeader==null){
            log.info("authorizationHeader is null");
            request.setAttribute("exception", BaseResponseStatus.JWT_AUTH_EMPTY);
         //    response.sendError(HttpServletResponse.SC_BAD_REQUEST,"JWT Token cannot be parsed");
            filterChain.doFilter(request, response);
            return;
        }else{
            if(authorizationHeader.startsWith("Bearer")){
                jwtToken = authorizationHeader.substring(7);
                if(jwtTokenProvider.validateToken(jwtToken)){ // 토큰 유효성 검사
                    if(jwtTokenProvider.isExpiredToken(jwtToken)){ // 만료 검사
                        request.setAttribute("exception",BaseResponseStatus.JWT_EXPIRED_ACCESS_TOKEN);
                        filterChain.doFilter(request,response);
                        return;
                    }
                }else {
                    request.setAttribute("exception", BaseResponseStatus.JWT_INVALID_ACCESS_TOKEN); // 실패
                    filterChain.doFilter(request, response);
                    return;
                }

            }
        }

        getAuthentication(jwtToken);
        filterChain.doFilter(request,response);

    }


    private boolean isAuthenticatedPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList,requestURI);
    }

    private void getAuthentication(String token){
        String email = jwtTokenProvider.getEmail(token);
        UserRole userRole = UserRole.valueOf(jwtTokenProvider.getRole(token));

        Authentication authToken = new UsernamePasswordAuthenticationToken(email,null,null);
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
