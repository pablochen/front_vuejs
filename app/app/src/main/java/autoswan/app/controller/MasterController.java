package autoswan.app.controller;

import autoswan.app.dto.DeptDto;
import autoswan.app.dto.PositionDto;
import autoswan.app.dto.UserDto;
import autoswan.app.dto.VacationDto;
import autoswan.app.entity.Dept;
import autoswan.app.entity.Position;
import autoswan.app.entity.User;
import autoswan.app.entity.Vacation;
import autoswan.app.repository.DeptRepository;
import autoswan.app.repository.PositionRepository;
import autoswan.app.repository.UserRepository;
import autoswan.app.repository.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/master")
public class MasterController {
    @Autowired
    EntityManager em;

    private final UserRepository userRepository;
    private final DeptRepository deptRepository;
    private final PositionRepository positionRepository;
    private final VacationRepository vacationRepository;

    @GetMapping("/getUserList")
    public ResponseEntity getUserList (final Pageable pageable){
        UserDto userDto = new UserDto();
        Page<UserDto> userList = userRepository.findUsers(userDto, pageable);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/setUser")
    public ResponseEntity setUser (@RequestBody UserDto userDto){
        Dept dept = em.getReference(Dept.class, userDto.getDeptId());
        Position position = em.getReference(Position.class, userDto.getPositionId());

        User user = new User(userDto.getUserCode(), userDto.getUserName(), dept, position, userDto.getJoinDate());
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/deleteUsers")
    public ResponseEntity deleteUsers (@RequestBody UserDto userDto){
        List<User> delUsers = userRepository.findAllByIdIn(userDto.getUserIds());
        for(User delUser: delUsers) {
            delUser.setUseYn("N");
            userRepository.save(delUser);
        }
        return new ResponseEntity<>(delUsers, HttpStatus.OK);
    }

    @GetMapping("/getDeptList")
    public ResponseEntity getDeptList (final Pageable pageable){
        Page<Dept> deptList = deptRepository.findByUseYnEquals("Y", pageable);
        return new ResponseEntity<>(deptList, HttpStatus.OK);
    }

    @PostMapping("/setDept")
    public ResponseEntity setDept (@RequestBody DeptDto deptDto){
        Dept dept = new Dept(deptDto.getDeptCode(), deptDto.getDeptName());
        deptRepository.save(dept);
        return new ResponseEntity<>(dept, HttpStatus.OK);
    }

    @GetMapping("/getPositionList")
    public ResponseEntity getPositionList (final Pageable pageable){
        Page<Position> positionList = positionRepository.findByUseYnEquals("Y", pageable);
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }

    @PostMapping("/setPosition")
    public ResponseEntity setDept (@RequestBody PositionDto positionDto){
        Position position = new Position(positionDto.getPositionCode(), positionDto.getPositionName());
        positionRepository.save(position);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @GetMapping("/getVacationList")
    public ResponseEntity getVacationList (final Pageable pageable){
        Page<Vacation> vacationList = vacationRepository.findByUseYnEquals("Y", pageable);
        return new ResponseEntity<>(vacationList, HttpStatus.OK);
    }

    @PostMapping("/setVacation")
    public ResponseEntity setVacation (@RequestBody VacationDto vacationDto){
        Vacation vacation = new Vacation(vacationDto.getVacationCode(), vacationDto.getVacationName(), vacationDto.getDays());
        vacationRepository.save(vacation);
        return new ResponseEntity<>(vacation, HttpStatus.OK);
    }

}
