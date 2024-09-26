package beyond.samdasoo.user.service;

import beyond.samdasoo.user.dto.JoinUserReq;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;


    public void join(JoinUserReq joinUserReq){
        Optional<User> byEmail = userRepository.findByEmail(joinUserReq.getEmail());
        if(byEmail.isPresent()){
            throw new IllegalArgumentException("이미 가입한 이메일");
        }

       User newUser = joinUserReq.toUser(encoder.encode(joinUserReq.getPassword()));

        userRepository.save(newUser);

    }
}
