package autoswan.app.repository;

import autoswan.app.dto.UserCond;
import autoswan.app.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom{
    public Page<UserDto> findUsers(UserCond userCond, Pageable pageable);
}
