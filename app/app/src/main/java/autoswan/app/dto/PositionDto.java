package autoswan.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionDto {
    private String positionCode;
    private String positionName;

    @QueryProjection
    public PositionDto(String positionCode, String positionName){
        this.positionCode = positionCode;
        this.positionName = positionName;
    }

}
