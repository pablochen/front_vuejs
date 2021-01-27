package autoswan.app.repository;

import autoswan.app.entity.VacationHist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationHistRepository extends JpaRepository<VacationHist, Integer>, VacationHistRepositoryCustom {
    List<VacationHist> findAllByIdIn(List<Integer> ids);
}
