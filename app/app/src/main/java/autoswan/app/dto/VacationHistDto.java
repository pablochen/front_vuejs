package autoswan.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class VacationHistDto {
    private int id;
    private String userCode;
    private String vacationCode;
    private String vacationName;
    private String startDate;
    private String endDate;
    private String useYn;

    private float days;
    private List<Integer> ids;

    @QueryProjection
    public VacationHistDto(int id, String userCode, String vacationCode, String vacationName, String startDate, String endDate, float days, String useYn){
        this.id = id;
        this.userCode = userCode;
        this.vacationCode = vacationCode;
        this.vacationName = vacationName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.useYn = useYn;
    }

    public VacationHistDto(String userCode){
        this.userCode = userCode;
    }

    public VacationHistDto(List<Integer> ids){
        this.ids = ids;
    }

}
