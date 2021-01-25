package autoswan.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VacationDto {
    private String vacationCode;
    private String vacationName;
    private float days;

    @QueryProjection
    public VacationDto(String vacationCode, String vacationName, float days){
        this.vacationCode = vacationCode;
        this.vacationName = vacationName;
        this.days = days;
    }

}
