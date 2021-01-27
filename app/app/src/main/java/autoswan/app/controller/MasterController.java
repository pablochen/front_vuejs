package autoswan.app.controller;

import autoswan.app.dto.*;
import autoswan.app.entity.Dept;
import autoswan.app.entity.Position;
import autoswan.app.entity.User;
import autoswan.app.entity.Vacation;
import autoswan.app.repository.DeptRepository;
import autoswan.app.repository.PositionRepository;
import autoswan.app.repository.UserRepository;
import autoswan.app.repository.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/master")
public class MasterController {

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
    public void setUser (@RequestBody UserDto userDto){
        User user = new User(userDto.getUserCode(), userDto.getUserName(), userDto.getDeptCode(), userDto.getPositionCode(), userDto.getJoinDate());
        userRepository.save(user);
    }

    @PostMapping("/deleteUsers")
    public void deleteUsers (@RequestBody UserDto userDto){
        List<User> delUsers = userRepository.findAllByIdIn(userDto.getIds());
        for(User delUser: delUsers) {
            delUser.setUseYn("N");
            userRepository.save(delUser);
        }
    }

    @GetMapping("/getDeptList")
    public ResponseEntity getDeptList (final Pageable pageable){
        Page<Dept> deptList = deptRepository.findByUseYnEquals("Y", pageable);
        return new ResponseEntity<>(deptList, HttpStatus.OK);
    }

    @PostMapping("/setDept")
    public void setDept (@RequestBody DeptDto deptDto){
        Dept dept = new Dept(deptDto.getDeptCode(), deptDto.getDeptName());
        deptRepository.save(dept);
    }

    @GetMapping("/getPositionList")
    public ResponseEntity getPositionList (final Pageable pageable){
        Page<Position> positionList = positionRepository.findByUseYnEquals("Y", pageable);
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }

    @PostMapping("/setPosition")
    public void setDept (@RequestBody PositionDto positionDto){
        Position position = new Position(positionDto.getPositionCode(), positionDto.getPositionName());
        positionRepository.save(position);
    }

    @GetMapping("/getVacationList")
    public ResponseEntity getVacationList (final Pageable pageable){
        Page<Vacation> vacationList = vacationRepository.findByUseYnEquals("Y", pageable);
        return new ResponseEntity<>(vacationList, HttpStatus.OK);
    }

    @PostMapping("/setVacation")
    public void setVacation (@RequestBody VacationDto vacationDto){
        Vacation vacation = new Vacation(vacationDto.getVacationCode(), vacationDto.getVacationName(), vacationDto.getDays());
        vacationRepository.save(vacation);
    }

}
