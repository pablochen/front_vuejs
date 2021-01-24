package autoswan.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(name="USER_SEQ_GEN", sequenceName="USER_SEQ", initialValue=1, allocationSize=1)
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GEN")
    private int id;

    @Column(name = "USER_CODE", columnDefinition = "VARCHAR(5)", unique = true)
    private String code;

    @Column(name = "USER_NAME", columnDefinition = "VARCHAR(30)")
    private String name;

    @Column(columnDefinition = "VARCHAR(5)")
    private String deptCode;

    @Column(columnDefinition = "VARCHAR(3)")
    private String positionCode;

    @Column(columnDefinition = "INT")
    private int totalVacCnt;

    @Column(columnDefinition = "VARCHAR(8)")
    private String joinDate;

    @Column(columnDefinition = "VARCHAR(1)")
    private String useYn;

    public User(String userCode, String userName, String deptCode, String positionCode, String joinDate){
        this.code = userCode;
        this.name = userName;
        this.deptCode = deptCode;
        this.positionCode = positionCode;
        this.totalVacCnt = 15;
        this.joinDate = joinDate;
        this.useYn = "Y";
    }

    @Override
    public String  toString() {
        String content = "code : " + this.code +
                "name : " + this.name +
                "code : " + this.code +
                "deptCode : " + this.deptCode +
                "positionCode : " + this.positionCode +
                "totalVacCnt : " + this.totalVacCnt +
                "joinDate : " + this.joinDate;
        return content;
    }

}
