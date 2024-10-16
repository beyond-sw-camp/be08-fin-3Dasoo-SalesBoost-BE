package beyond.samdasoo.test;


import beyond.samdasoo.common.jwt.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final RefreshTokenRepository refreshTokenRepository;

    @GetMapping
    public String test(){
        return "test";
    }

}
