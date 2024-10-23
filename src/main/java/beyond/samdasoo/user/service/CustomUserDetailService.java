package beyond.samdasoo.user.service;

import beyond.samdasoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override  // 필터를 통해 자동으로 db에 있는 값을 가져와서 검증
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return null;
    }
}
