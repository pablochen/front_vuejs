package autoswan.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeptDto {
    private String deptCode;
    private String deptName;

    @QueryProjection
    public DeptDto(String deptCode, String deptName){
        this.deptCode = deptCode;
        this.deptName = deptName;
    }

}
