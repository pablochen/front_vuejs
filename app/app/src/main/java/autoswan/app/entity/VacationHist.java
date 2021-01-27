package autoswan.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(name="VACATION_HIST_SEQ_GEN", sequenceName="VACATION_HIST_SEQ", initialValue=1, allocationSize=1)
public class VacationHist {
    @Id
    @Column(name = "VACATION_HIST_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VACATION_HIST_SEQ_GEN")
    private int id;

    @Column(columnDefinition = "VARCHAR(5)")
    private String userCode;

    @Column(columnDefinition = "VARCHAR(3)")
    private String vacationCode;

    @Column(columnDefinition = "VARCHAR(8)")
    private String startDate;

    @Column(columnDefinition = "VARCHAR(8)")
    private String endDate;

    @Column(columnDefinition = "FLOAT")
    private float days;

    @Column(columnDefinition = "VARCHAR(1)")
    private String useYn;

    public VacationHist(String userCode, String vacationCode, String startDate, String endDate, float days){
        this.userCode = userCode;
        this.vacationCode = vacationCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.useYn = "Y";
    }
}
