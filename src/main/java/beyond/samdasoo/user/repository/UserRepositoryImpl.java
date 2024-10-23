package beyond.samdasoo.user.repository;

import beyond.samdasoo.admin.entity.QDepartment;
import beyond.samdasoo.user.dto.FilterUserDto;
import beyond.samdasoo.user.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<FilterUserDto> findUsersByDepartmentAndSubDepartments(Long deptNo) {
        QUser user = QUser.user;

        // 주어진 deptNo를 포함한 모든 하위 부서까지의 deptNo 리스트를 재귀적으로 조회
        List<Long> deptNos = findAllSubDepartments(deptNo);

        return queryFactory
                .select(Projections.constructor(FilterUserDto.class, user.id, user.name))
                .from(user)
                .where(user.department.deptNo.in(deptNos))
                .fetch();
    }

    private List<Long> findAllSubDepartments(Long deptNo) {
        List<Long> deptNos = new ArrayList<>();
        QDepartment department = QDepartment.department;
        deptNos.add(deptNo);

        List<Long> subDeptNos = queryFactory
                .select(department.deptNo)
                .from(department)
                .where(department.parent.deptNo.eq(deptNo))
                .fetch();

        for (Long subDeptNo : subDeptNos) {
            deptNos.addAll(findAllSubDepartments(subDeptNo));
        }

        return deptNos;
    }
}
