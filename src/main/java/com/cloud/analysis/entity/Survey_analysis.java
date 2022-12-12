package com.cloud.analysis.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class Survey_analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id", nullable = false)
    private Integer pkId;

    @Column(name = "analysis_id")
    private Integer analysisId;

    @Column(name = "sur_id", nullable = false)
    private Integer surveyId;

    @Column(length = 45)
    private String subject;

    @Column(name = "reg_dt")
    private LocalDateTime regDt;

    @Column(name = "reg_id",length = 20)
    private String regId;

    @Column()
    private Integer type;
}
