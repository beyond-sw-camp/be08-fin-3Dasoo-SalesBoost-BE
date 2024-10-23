package beyond.samdasoo.user.repository;

import beyond.samdasoo.admin.entity.Department;
import beyond.samdasoo.admin.repository.DepartmentRepository;
import beyond.samdasoo.common.config.QuerydslConfig;
import beyond.samdasoo.user.dto.UserRole;
import beyond.samdasoo.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
@Import(QuerydslConfig.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    @Test
    @DisplayName("이메일을 통해 유저의 데이터를 가져올 수 있다")
    void findByEmail(){
        // given
        Department department = Department.builder().deptName("영업부").deptCode("101").engName("sale").deptHead("홍길동").build();
        departmentRepository.save(department);
        Department find = departmentRepository.findByDeptName("영업부").get();
        User user = User.builder()
                .name("김은경")
                .email("test@gmail.com")
                .password("1234")
                .department(find)
                .employeeId("20241010020")
                .role(UserRole.USER)
                .build();

        userRepository.save(user);

        // when
        Optional<User> findUser = userRepository.findByEmail("test@gmail.com");

        // then
        assertThat(findUser.isPresent()).isTrue();
    }


    @Test
    @DisplayName("이메일과 비밀번호 정보로 가입한 유저 데이터를 가져올 수 있다")
    void findByEmailAndPassword(){
        // given
        Department department = Department.builder().deptName("영업부").deptCode("101").engName("sale").deptHead("홍길동").build();
        departmentRepository.save(department);
        Department find = departmentRepository.findByDeptName("영업부").get();
        User user = User.builder()
                .name("김은경")
                .email("test@gmail.com")
                .password("1234")
                .department(find)
                .employeeId("20241010020")
                .role(UserRole.USER)
                .build();

        userRepository.save(user);

        // when
        Optional<User> findUser = userRepository.findByEmailAndPassword("test@gmail.com", "1234");

        // then
        assertThat(findUser.isPresent()).isTrue();
    }

}
