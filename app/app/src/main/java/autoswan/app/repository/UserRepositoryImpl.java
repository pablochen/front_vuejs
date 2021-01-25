package autoswan.app.repository;

import autoswan.app.dto.QUserDto;
import autoswan.app.dto.UserDto;
import autoswan.app.entity.QDept;
import autoswan.app.entity.QPosition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static autoswan.app.entity.QDept.dept;
import static autoswan.app.entity.QPosition.position;
import static autoswan.app.entity.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<UserDto> findUsers(UserDto userDto, Pageable pageable){
        QDept qDept = dept;
        QPosition qPosition = position;

        List<UserDto> content = queryFactory
                .select(new QUserDto(user.id, user.code, user.name, user.deptCode, dept.name, user.positionCode, position.name, user.totalVacCnt, user.joinDate))
                .from(user)
                .leftJoin(dept)
                    .on(user.deptCode.eq(dept.code))
                .leftJoin(position)
                    .on(user.positionCode.eq(position.code))
                .where(
                        userIdEq(userDto.getId()),
                        userAlive()
                )
                .orderBy(user.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(new QUserDto(user.id, user.code, user.name, user.deptCode, dept.name, user.positionCode, position.name, user.totalVacCnt, user.joinDate))
                .from(user)
                .where(
                        userIdEq(userDto.getId()),
                        userAlive()
                )
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }

    public BooleanExpression userIdEq(Integer userId){
        return (userId != null && userId != 0) ? user.id.eq(userId) : null;
    }

    public BooleanExpression userAlive(){
        return user.useYn.eq("Y");
    }


}
