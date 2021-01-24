package autoswan.app.repository;

import autoswan.app.dto.QUserDto;
import autoswan.app.dto.UserCond;
import autoswan.app.dto.UserDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static autoswan.app.entity.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<UserDto> findUsers(UserCond userCond, Pageable pageable){

        List<UserDto> content = queryFactory
                .select(new QUserDto(user.id, user.code, user.name, user.deptCode, user.positionCode, user.totalVacCnt, user.joinDate))
                .from(user)
                .where(
                        userIdEq(userCond.getId()),
                        userAlive()
                )
                .orderBy(user.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(new QUserDto(user.id, user.code, user.name, user.deptCode, user.positionCode, user.totalVacCnt, user.joinDate))
                .from(user)
                .where(
                        userIdEq(userCond.getId()),
                        userAlive()
                )
                .orderBy(user.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
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
