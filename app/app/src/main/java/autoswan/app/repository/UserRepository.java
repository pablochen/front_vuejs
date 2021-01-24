package autoswan.app.repository;

import autoswan.app.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
    Page<User> findByUseYnEquals(String useYn, Pageable pageable);
}
