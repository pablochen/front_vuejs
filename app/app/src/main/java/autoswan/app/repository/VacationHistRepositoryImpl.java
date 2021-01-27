package autoswan.app.repository;

import autoswan.app.dto.QVacationHistDto;
import autoswan.app.dto.VacationHistDto;
import autoswan.app.entity.QVacation;
import autoswan.app.entity.QVacationHist;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static autoswan.app.entity.QVacation.vacation;
import static autoswan.app.entity.QVacationHist.vacationHist;
import static org.springframework.util.StringUtils.hasText;

public class VacationHistRepositoryImpl implements VacationHistRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public VacationHistRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<VacationHistDto> findHists(VacationHistDto vacationHistDto, Pageable pageable) {
        QVacation qVacation = vacation;
        QVacationHist qVacationHist = vacationHist;

        List<VacationHistDto> content = queryFactory
                .select(new QVacationHistDto(vacationHist.id, vacationHist.userCode, vacationHist.vacationCode, vacation.name,
                        vacationHist.startDate, vacationHist.endDate, vacationHist.days, vacationHist.useYn))
                .from(vacationHist)
                .leftJoin(vacation)
                    .on(vacationHist.vacationCode.eq(vacation.code))
                .where(
                        userCodeEq(vacationHistDto.getUserCode())
                )
                .orderBy(vacationHist.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(new QVacationHistDto(vacationHist.id, vacationHist.userCode, vacationHist.vacationCode, vacation.name,
                        vacationHist.startDate, vacationHist.endDate, vacationHist.days, vacationHist.useYn))
                .from(vacationHist)
                .leftJoin(vacation)
                    .on(vacationHist.vacationCode.eq(vacation.code))
                .where(
                        userCodeEq(vacationHistDto.getUserCode())
                )
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }

    public BooleanExpression userCodeEq(String userCode){
        return hasText(userCode) ? vacationHist.userCode.eq(userCode) : null;
    }

}
