package beyond.samdasoo.user.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static beyond.samdasoo.common.response.BaseResponseStatus.EMAIL_OR_PWD_NOT_FOUND;
import static beyond.samdasoo.common.response.BaseResponseStatus.EMPLOYEE_ID_NOT_VALID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override  // 필터를 통해 자동으로 db에 있는 값을 가져와서 검증
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        if(username.contains("@")){
            user = userRepository.findByEmail(username)
                    .orElseThrow(()-> new BaseException(EMAIL_OR_PWD_NOT_FOUND));
        }else{
            user = userRepository.findByEmployeeId(username)
                    .orElseThrow(() -> new BaseException(EMPLOYEE_ID_NOT_VALID));
        }

        return null;
    }
}
