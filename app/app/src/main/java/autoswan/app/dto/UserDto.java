package autoswan.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private int id;
    private String userCode;
    private String userName;
    private String deptCode;
    private String deptName;
    private String positionCode;
    private String positionName;
    private int totalVacCnt;
    private String joinDate;

    private List<Integer> ids;

    @QueryProjection
    public UserDto(int userId, String userCode, String userName, String deptCode, String deptName, String positionCode, String positionName, int totalVacCnt, String joinDate){
        this.id = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.positionCode = positionCode;
        this.positionName = positionName;
        this.totalVacCnt = totalVacCnt;
        this.joinDate = joinDate;
    }

}
