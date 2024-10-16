package beyond.samdasoo.user.service;

import beyond.samdasoo.admin.entity.Department;
import beyond.samdasoo.admin.repository.DepartmentRepository;
import beyond.samdasoo.common.email.CertificationNumber;
import beyond.samdasoo.common.email.EmailProvider;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.jwt.JwtTokenProvider;
import beyond.samdasoo.common.jwt.RefreshTokenRepository;
import beyond.samdasoo.common.jwt.entity.RefreshToken;
import beyond.samdasoo.common.jwt.service.RefreshTokenService;
import beyond.samdasoo.user.dto.*;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import static beyond.samdasoo.common.response.BaseResponseStatus.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final DepartmentRepository departmentRepository;
    private final RefreshTokenService refreshTokenService;
    private final EmailProvider emailProvider;


    public JoinUserRes join(JoinUserReq joinUserReq){

        Optional<User> byEmail = userRepository.findByEmail(joinUserReq.getEmail());
        if(byEmail.isPresent()){
            throw new BaseException(EMAIL_ALREADY_EXIST);
        }

        Department department = departmentRepository.findByDeptName(joinUserReq.getDeptName())
                .orElseThrow(() -> new BaseException(DEPARTMENT_NOT_EXIST));

        User newUser = joinUserReq.toUser(encoder.encode(joinUserReq.getPassword()),generateEmployeeId(),department);

        User saveUser = userRepository.save(newUser);

        return new JoinUserRes(saveUser.getEmployeeId());
    }

    public TokenResult login(LoginUserReq loginUserReq){
        String type = loginUserReq.getLoginType();

        User findUser = null;

        if(type.equals("email")){ // 이메일 로그인
             findUser = userRepository.findByEmail(loginUserReq.getEmail())
                    .orElseThrow(()->new BaseException(EMAIL_OR_PWD_NOT_FOUND));
        }else if(type.equals("employeeId")){
            findUser = userRepository.findByEmployeeId(loginUserReq.getEmployeeId())
                    .orElseThrow(()->new BaseException(EMPLOYEE_ID_NOT_VALID));
        }else{
            throw new BaseException(LOGIN_TYPE_NOT_VALID);
        }


        boolean matches = encoder.matches(loginUserReq.getPassword(), findUser.getPassword());

        if(!matches){
            throw new BaseException(EMAIL_OR_PWD_NOT_FOUND);
        }

        String accessToken = jwtTokenProvider.createToken(findUser.getEmail(), findUser.getRole().toString(),"ACCESS");
        String refreshToken = jwtTokenProvider.createToken(findUser.getEmail(),findUser.getRole().toString(),"REFRESH");

        refreshTokenService.saveToken(findUser.getEmail(), refreshToken);

        return new TokenResult(accessToken,refreshToken, findUser.getName(), findUser.getEmail(), findUser.getRole(),findUser.getDepartment().getDeptName());
    }

    public UserDto getUser(String email) {
     //   User findUser = userRepository.findById(userId).orElseThrow(()->new BaseException(USER_NOT_EXIST));
        User findUser = userRepository.findByEmail(email).orElseThrow(()->new BaseException(USER_NOT_EXIST));

        return new UserDto(findUser.getName(), findUser.getEmail(),findUser.getRole());
    }


    private String generateEmployeeId(){
        // 사번 생성 : 회원가입 날짜(년월) + 가입 순서
        LocalDate today = LocalDate.now();
        String datePrefix = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 오늘 날짜에 해당하는 마지막 사번 조회 (없으면 0부터 시작)
        int countForToday = userRepository.countByJoinDate(today);
        int nextSequence = countForToday + 1;

        String employeeId = datePrefix + String.format("%03d", nextSequence);
        log.info("Generated employee id {}", employeeId);
        return employeeId;
    }


    // 토큰 재발급
    public ReIssueResult reissue(Cookie refreshTokenCookie, HttpServletRequest request){
        String refreshToken = refreshTokenCookie.getValue();

        //  리프레시 토큰이 유효한 경우 액세스토큰, 리프레시 토큰 모두 재발급
        if(jwtTokenProvider.validateToken(refreshToken,request)) {

            String userEmail = jwtTokenProvider.getEmail(refreshToken);
            String role = jwtTokenProvider.getRole(refreshToken);

            boolean exists = refreshTokenService.existsByEmailAndToken(userEmail, refreshToken);
            if(!exists){
                throw new BaseException(JWT_INVALID_REFRESH_TOKEN);
            }

            refreshTokenService.deleteByEmailAndToken(userEmail,refreshToken);
            refreshTokenService.saveToken(userEmail,refreshToken);

            String refresh = jwtTokenProvider.createToken(userEmail, role, "REFRESH");
            String access = jwtTokenProvider.createToken(userEmail, role, "ACCESS");


            return new ReIssueResult(access,refresh);

        }
        throw new BaseException(JWT_INVALID_REFRESH_TOKEN);
    }

    public String logout(Cookie refreshCookie){
        final String  LOGOUT_RESULT = "로그아웃 완료";

        if(refreshCookie==null){
            return LOGOUT_RESULT;
        }

        String refreshToken = refreshCookie.getValue();

        String userEmail = jwtTokenProvider.getEmail(refreshToken);

        // 캐시에서 토큰 제거
        refreshTokenService.deleteByEmailAndToken(userEmail,refreshToken);

        return LOGOUT_RESULT;

    }

    public void sendEmailCode(String email) {
        boolean exists = userRepository.existsByEmail(email);

        if(exists){
            throw new BaseException(EMAIL_ALREADY_EXIST);
        }

        String certificationNumber = CertificationNumber.getCertificationNumber();
        boolean isSucceed = emailProvider.sendCertificationMail(email, certificationNumber);

        if(!isSucceed){
            throw new BaseException(FAIL_SEND_CODE);
        }

        // todo : Redis 연결
    }
}
