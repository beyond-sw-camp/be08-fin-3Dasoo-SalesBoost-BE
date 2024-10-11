package beyond.samdasoo.common.initDB;

import beyond.samdasoo.admin.entity.Department;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitUserService initUserService;

    @PostConstruct
    public void init(){


    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitUserService{

        // 부서 생성


        // 유저 생성


        // 고객 생성

    }
}
