package autoswan.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VacationDto {
    private int vacationId;
    private String vacationCode;
    private String vacationName;
    private float days;

    @QueryProjection
    public VacationDto(int vacationId, String vacationCode, String vacationName, float days){
        this.vacationId = vacationId;
        this.vacationCode = vacationCode;
        this.vacationName = vacationName;
        this.days = days;
    }

}
