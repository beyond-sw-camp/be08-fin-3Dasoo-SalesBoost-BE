package beyond.samdasoo.user.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.user.dto.JoinUserReq;
import beyond.samdasoo.user.dto.LoginUserReq;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static beyond.samdasoo.common.response.BaseResponseStatus.EMAIL_ALREADY_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.EMAIL_OR_PWD_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;


    public void join(JoinUserReq joinUserReq){
        Optional<User> byEmail = userRepository.findByEmail(joinUserReq.getEmail());
        if(byEmail.isPresent()){
            throw new BaseException(EMAIL_ALREADY_EXIST);
        }

       User newUser = joinUserReq.toUser(encoder.encode(joinUserReq.getPassword()));

        userRepository.save(newUser);

    }

    public void login(LoginUserReq loginUserReq){

        User findUser = userRepository.findByEmail(loginUserReq.getEmail())
                .orElseThrow(()->new BaseException(EMAIL_OR_PWD_NOT_FOUND));

        boolean matches = encoder.matches(loginUserReq.getPassword(), findUser.getPassword());

        if(!matches){
            throw new BaseException(EMAIL_ALREADY_EXIST);
        }

        // todo : jwt 토큰 발급
    }
}
