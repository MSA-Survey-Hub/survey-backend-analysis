package com.cloud.analysis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Survey_analysis_option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id", nullable = false)
    private Integer optionId;

    @Column(name = "analysis_id")
    private Integer analysisId;

    @Column(name = "survey_id", nullable = false)
    private Integer surveyId;

    @Column(name = "option_name", nullable = false)
    private String optionName;

    @Column(nullable = false)
    private Integer value;
}
