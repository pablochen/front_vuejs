package autoswan.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeptDto {
    private int deptId;
    private String deptCode;
    private String deptName;

    @QueryProjection
    public DeptDto(int deptId, String deptCode, String deptName){
        this.deptId = deptId;
        this.deptCode = deptCode;
        this.deptName = deptName;
    }
}
