package autoswan.app.controller;

import autoswan.app.dto.VacationHistDto;
import autoswan.app.entity.User;
import autoswan.app.entity.Vacation;
import autoswan.app.entity.VacationHist;
import autoswan.app.repository.UserRepository;
import autoswan.app.repository.VacationHistRepository;
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
@RequestMapping("/vacation")
public class VacationController {
    @Autowired
    EntityManager em;

    private final VacationHistRepository vacationHistRepository;
    private final UserRepository userRepository;

    @GetMapping("/getVacationHistList")
    public ResponseEntity getVacationHistList (@RequestParam(name="userId", required = false) int userId, final Pageable pageable){
        VacationHistDto vacationHistDto = new VacationHistDto(userId);
        Page<VacationHistDto> vacationHistList = vacationHistRepository.findHists(vacationHistDto, pageable);
        return new ResponseEntity<>(vacationHistList, HttpStatus.OK);
    }

    @PostMapping("/setVacationHist")
    public ResponseEntity setVacationHist (@RequestBody VacationHistDto vacationHistDto){
        User user = em.getReference(User.class, vacationHistDto.getUserId());
        Vacation vacation = em.getReference(Vacation.class, vacationHistDto.getVacationId());

        VacationHist vacationHist = new VacationHist(user, vacation, vacationHistDto.getStartDate(), vacationHistDto.getEndDate(), vacationHistDto.getDays());
        vacationHistRepository.save(vacationHist);

        User updateUser = userRepository.findById(vacationHistDto.getUserId()).get();
        user.setRemainVacCnt(updateUser.getRemainVacCnt() - vacationHistDto.getDays());
        userRepository.save(updateUser);
        return new ResponseEntity<>(vacationHist, HttpStatus.OK);
    }

    @PutMapping("/deleteVacationHists")
    public ResponseEntity deleteVacationHists (@RequestBody VacationHistDto vacationHistDto){
        System.out.println(vacationHistDto.getVacationHistIds());
        List<VacationHist> delVacationHists = vacationHistRepository.findAllByIdIn(vacationHistDto.getVacationHistIds());
        for(VacationHist delVacationHist: delVacationHists) {
            delVacationHist.setUseYn("N");
            vacationHistRepository.save(delVacationHist);

            User user = userRepository.findById(delVacationHist.getUser().getId()).get();
            user.setRemainVacCnt(user.getRemainVacCnt() + delVacationHist.getDays());
            userRepository.save(user);
        }
        return new ResponseEntity<>(delVacationHists, HttpStatus.OK);
    }
}
