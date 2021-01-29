package autoswan.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class VacationHistDto {
    private int vacationHistId;
    private int userId;
    private String userCode;
    private String userName;
    private int vacationId;
    private String vacationCode;
    private String vacationName;
    private String startDate;
    private String endDate;
    private String useYn;

    private float days;
    private List<Integer> vacationHistIds;

    @QueryProjection
    public VacationHistDto(int vacationHistId,
                           int userId, String userCode, String userName,
                           int vacationId, String vacationCode, String vacationName,
                           String startDate, String endDate, float days, String useYn){
        this.vacationHistId = vacationHistId;
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.vacationId = vacationId;
        this.vacationCode = vacationCode;
        this.vacationName = vacationName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.useYn = useYn;
    }

    public VacationHistDto(int userId){
        this.userId = userId;
    }

    public VacationHistDto(List<Integer> vacationHistIds){
        this.vacationHistIds = vacationHistIds;
    }

}
