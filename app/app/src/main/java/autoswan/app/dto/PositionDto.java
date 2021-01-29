package autoswan.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionDto {
    private int positionId;
    private String positionCode;
    private String positionName;

    @QueryProjection
    public PositionDto(int positionId, String positionCode, String positionName){
        this.positionId = positionId;
        this.positionCode = positionCode;
        this.positionName = positionName;
    }

}
