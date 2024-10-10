package beyond.samdasoo.user.service;

import beyond.samdasoo.admin.entity.Department;
import beyond.samdasoo.admin.repository.DepartmentRepository;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.jwt.JwtTokenProvider;
import beyond.samdasoo.user.dto.*;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public LoginUserRes login(LoginUserReq loginUserReq){
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

        return new LoginUserRes(accessToken,findUser.getName(), findUser.getEmail(), findUser.getRole());
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
}
