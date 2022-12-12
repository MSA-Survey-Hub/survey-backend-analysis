package com.cloud.analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Survey_analysis_option_DTO {
    private Integer optionId;
    private String optionName;
    private Integer value;
    private Integer analysis_id;
    private Integer survey_id;
}
