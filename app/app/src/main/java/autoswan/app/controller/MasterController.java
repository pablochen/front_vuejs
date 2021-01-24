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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Page<User> userList = userRepository.findByUseYnEquals("Y", pageable);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/setUser")
    public void setUser (@RequestBody UserDto userDto){
        User user = new User(userDto.getUserCode(), userDto.getUserName(), userDto.getDeptCode(), userDto.getPositionCode(), userDto.getJoinDate());
        userRepository.save(user);
    }

    @GetMapping("/deleteUser")
    public void deleteUser (User user){
        User delUser = userRepository.findById(user.getId()).get();
        delUser.setUseYn("N");
        userRepository.save(user);
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

    @GetMapping("/deleteDept")
    public void deleteDept (Dept dept){
        Dept delDept = deptRepository.findById(dept.getId()).get();
        delDept.setUseYn("N");
        deptRepository.save(delDept);
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

    @GetMapping("/deletePosition")
    public void deletePosition (Position position){
        Position delPosition = positionRepository.findById(position.getId()).get();
        delPosition.setUseYn("N");
        positionRepository.save(delPosition);
    }

    @GetMapping("/getVacationList")
    public ResponseEntity getVacationList (final Pageable pageable){
        Page<Vacation> vacationList = vacationRepository.findByUseYnEquals("Y", pageable);
        return new ResponseEntity<>(vacationList, HttpStatus.OK);
    }

    @PostMapping("/setVacation")
    public void setDept (@RequestBody VacationDto vacationDto){
        Vacation vacation = new Vacation(vacationDto.getVacationCode(), vacationDto.getVacationName(), vacationDto.getDays());
        vacationRepository.save(vacation);
    }

    @GetMapping("/deleteVacation")
    public void deleteUser (Vacation vacation){
        Vacation delVacation = vacationRepository.findById(vacation.getId()).get();
        delVacation.setUseYn("N");
        vacationRepository.save(delVacation);
    }

}
