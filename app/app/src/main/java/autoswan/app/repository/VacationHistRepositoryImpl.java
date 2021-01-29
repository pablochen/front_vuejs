package autoswan.app.repository;

import autoswan.app.dto.QVacationHistDto;
import autoswan.app.dto.VacationHistDto;
import autoswan.app.entity.QUser;
import autoswan.app.entity.QVacation;
import autoswan.app.entity.QVacationHist;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static autoswan.app.entity.QUser.user;
import static autoswan.app.entity.QVacation.vacation;
import static autoswan.app.entity.QVacationHist.vacationHist;

public class VacationHistRepositoryImpl implements VacationHistRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public VacationHistRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    @Transactional(readOnly=true, propagation = Propagation.SUPPORTS)
    public Page<VacationHistDto> findHists(VacationHistDto vacationHistDto, Pageable pageable) {
        QUser qUser = user;
        QVacation qVacation = vacation;
        QVacationHist qVacationHist = vacationHist;

        List<VacationHistDto> content = queryFactory
                .select(new QVacationHistDto(vacationHist.id, vacationHist.user.id, user.code, user.name,
                                            vacationHist.vacation.id, vacation.code, vacation.name,
                                            vacationHist.startDate, vacationHist.endDate, vacationHist.days, vacationHist.useYn))
                .from(user)
                .leftJoin(vacationHist)
                    .on(user.id.eq(vacationHist.user.id))
                .leftJoin(vacation)
                    .on(vacationHist.vacation.id.eq(vacation.id))
                .where(
                        userIdEq(vacationHistDto.getUserId())
                )
                .orderBy(vacationHist.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(new QVacationHistDto(vacationHist.id, vacationHist.user.id, user.code, user.name,
                        vacationHist.vacation.id, vacation.code, vacation.name,
                        vacationHist.startDate, vacationHist.endDate, vacationHist.days, vacationHist.useYn))
                .from(user)
                .leftJoin(vacationHist)
                    .on(user.id.eq(vacationHist.user.id))
                .leftJoin(vacation)
                    .on(vacationHist.vacation.id.eq(vacation.id))
                .where(
                        userIdEq(vacationHistDto.getUserId())
                )
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }

    public BooleanExpression userIdEq(Integer userId){
        return (userId!=null && userId!=0) ? vacationHist.user.id.eq(userId) : null;
    }

}
