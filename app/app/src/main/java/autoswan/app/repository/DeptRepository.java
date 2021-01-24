package autoswan.app.repository;

import autoswan.app.entity.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, Integer>{
    Page<Dept> findByUseYnEquals(String useYn, Pageable pageable);
}
