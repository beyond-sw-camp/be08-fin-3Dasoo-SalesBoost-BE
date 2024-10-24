package beyond.samdasoo.common.jwt;

import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.user.CustomUserDetails;
import beyond.samdasoo.user.dto.UserRole;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.service.CustomUserDetailService;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // JWT 검증 필터 -> 헤더로 들어온 jwt 토큰을 검증

    private static String[] whiteList={"/api/users/login","/api/users/join","/api/users/reissue"};
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailService customUserDetailService;
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
                if(jwtTokenProvider.validateToken(jwtToken,request)){ // 토큰 유효성 검사
                    getAuthentication(jwtToken);
                    filterChain.doFilter(request,response);
                    return;
                }else {
                    filterChain.doFilter(request, response);
                    return;
                }

            }
        }


        filterChain.doFilter(request,response);

    }


    private boolean isAuthenticatedPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList,requestURI);
    }

    private void getAuthentication(String token){
        String email = jwtTokenProvider.getEmail(token);
        UserRole userRole = UserRole.valueOf(jwtTokenProvider.getRole(token));

        UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtTokenProvider.getEmail(token));

        Authentication authToken = new UsernamePasswordAuthenticationToken(email,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

    }
}
