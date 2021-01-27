package autoswan.app.controller;

import autoswan.app.dto.VacationHistDto;
import autoswan.app.entity.User;
import autoswan.app.entity.VacationHist;
import autoswan.app.repository.UserRepository;
import autoswan.app.repository.VacationHistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vacation")
public class VacationController {

    private final VacationHistRepository vacationHistRepository;
    private final UserRepository userRepository;

    @GetMapping("/getVacationHistList")
    public ResponseEntity getVacationHistList (@RequestParam(name="userCode") String userCode, final Pageable pageable){
        VacationHistDto vacationHistDto = new VacationHistDto(userCode);
        Page<VacationHistDto> vacationHistList = vacationHistRepository.findHists(vacationHistDto, pageable);
        return new ResponseEntity<>(vacationHistList, HttpStatus.OK);
    }

    @PostMapping("/setVacationHist")
    public void setVacationHist (@RequestBody VacationHistDto vacationHistDto){
        VacationHist vacationHist = new VacationHist(vacationHistDto.getUserCode(), vacationHistDto.getVacationCode(),
                                                        vacationHistDto.getStartDate(), vacationHistDto.getEndDate(), vacationHistDto.getDays());
        vacationHistRepository.save(vacationHist);

        User user = userRepository.findByCodeEquals(vacationHistDto.getUserCode());
        user.setRemainVacCnt(user.getRemainVacCnt() - vacationHistDto.getDays());
        userRepository.save(user);
    }

    @PostMapping("/deleteVacationHists")
    public void deleteVacationHists (@RequestBody VacationHistDto vacationHistDto){
        List<VacationHist> delVacationHists = vacationHistRepository.findAllByIdIn(vacationHistDto.getIds());
        for(VacationHist delVacationHist: delVacationHists) {
            delVacationHist.setUseYn("N");
            vacationHistRepository.save(delVacationHist);

            User user = userRepository.findByCodeEquals(delVacationHist.getUserCode());
            user.setRemainVacCnt(user.getRemainVacCnt() + delVacationHist.getDays());
            userRepository.save(user);
        }
    }
}
