package beyond.samdasoo.user.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.jwt.JwtTokenProvider;
import beyond.samdasoo.user.dto.JoinUserReq;
import beyond.samdasoo.user.dto.LoginUserReq;
import beyond.samdasoo.user.dto.LoginUserRes;
import beyond.samdasoo.user.dto.UserDto;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static beyond.samdasoo.common.response.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public void join(JoinUserReq joinUserReq){
        Optional<User> byEmail = userRepository.findByEmail(joinUserReq.getEmail());
        if(byEmail.isPresent()){
            throw new BaseException(EMAIL_ALREADY_EXIST);
        }

       User newUser = joinUserReq.toUser(encoder.encode(joinUserReq.getPassword()));

        userRepository.save(newUser);

    }

    public LoginUserRes login(LoginUserReq loginUserReq){

        User findUser = userRepository.findByEmail(loginUserReq.getEmail())
                .orElseThrow(()->new BaseException(EMAIL_OR_PWD_NOT_FOUND));

        boolean matches = encoder.matches(loginUserReq.getPassword(), findUser.getPassword());

        if(!matches){
            throw new BaseException(EMAIL_ALREADY_EXIST);
        }

        String accessToken = jwtTokenProvider.createToken(findUser.getEmail(), findUser.getRole().toString(),"ACCESS");

        return new LoginUserRes(accessToken,findUser.getName(), findUser.getEmail(), findUser.getRole());
    }

    public UserDto getUser(Long userId) {

        User findUser = userRepository.findById(userId).orElseThrow(()->new BaseException(USER_NOT_EXIST));

        return new UserDto(findUser.getName(), findUser.getEmail(),findUser.getRole());
    }
}
