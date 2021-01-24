package autoswan.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private int id;
    private String userCode;
    private String userName;
    private String deptCode;
    private String positionCode;
    private int totalVacCnt;
    private String joinDate;

    @QueryProjection
    public UserDto(int userId, String userCode, String userName, String deptCode, String positionCode, int totalVacCnt, String joinDate){
        this.id = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.deptCode = deptCode;
        this.positionCode = positionCode;
        this.totalVacCnt = totalVacCnt;
        this.joinDate = joinDate;
    }

}
