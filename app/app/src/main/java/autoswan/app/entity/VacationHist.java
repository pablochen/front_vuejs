package autoswan.app.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(name="VACATION_HIST_SEQ_GEN", sequenceName="VACATION_HIST_SEQ", initialValue=1, allocationSize=1)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class VacationHist {
    @Id
    @Column(name = "VACATION_HIST_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VACATION_HIST_SEQ_GEN")
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vacation_id")
    private Vacation vacation;

    @Column(columnDefinition = "VARCHAR(8)")
    private String startDate;

    @Column(columnDefinition = "VARCHAR(8)")
    private String endDate;

    @Column(columnDefinition = "FLOAT")
    private float days;

    @Column(columnDefinition = "VARCHAR(1)")
    private String useYn;

    public VacationHist(User user, Vacation vacation, String startDate, String endDate, float days){
        this.user = user;
        this.vacation = vacation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.useYn = "Y";
    }
}
