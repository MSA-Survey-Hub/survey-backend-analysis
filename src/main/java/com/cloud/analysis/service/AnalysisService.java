package com.cloud.analysis.service;

import com.cloud.analysis.dto.Platform_analysis_DTO;
import com.cloud.analysis.dto.Survey_analysis_DTO;
import com.cloud.analysis.dto.User.UserDTO;
import com.cloud.analysis.entity.Platform_analysis;
import com.cloud.analysis.entity.Platform_analysis_option;
import com.cloud.analysis.entity.Survey_analysis;
import com.cloud.analysis.entity.Survey_analysis_option;

import java.util.List;
import java.util.Map;

public interface AnalysisService {

    Map<Object,Object> getSurveyAnalysisOptionList(Integer analysisId, Integer surveyId);
    List<Platform_analysis_option> getPlatformAnalysisList(Integer surId);

    List<Object> getTypeSubjectList(Integer surveyId);

//    Map<String, Object> Test();

    void AgeUpdate(List<UserDTO> answerUserList);
    void JobUpdate(List<UserDTO> answerUserList);
    void GenderUpdate(List<UserDTO> answerUserList);
    void TimeUpdate(List<Map<String,Object>> answerDataList);

    void SurveyCntUpdate(Map<String,Object> survey_info);
    void SurveyCategoryUpdate(Map<String,Object> survey_info);

    void InsertVulgarism(int surID, boolean InfoYn, boolean QuestionYn);

    void InsertSurveyAnalysisOption(List<Map<String,Object>> question_list, Integer surId);

    Map<String, Object> CheckVulgarism(Map<String,Object> survey_info, List<Map<String,Object>> question_list);


    default Platform_analysis_DTO TransferEntityToDTO(Platform_analysis platform_analysis) {
        Platform_analysis_DTO dto = Platform_analysis_DTO.builder()
                .type(platform_analysis.getType())
                .subject(platform_analysis.getSubject())
                .build();
        return dto;
    }

    default Survey_analysis_DTO TransferEntityToDTO(Survey_analysis survey_analysis) {
        Survey_analysis_DTO dto = Survey_analysis_DTO.builder()
                .type(survey_analysis.getType())
                .subject(survey_analysis.getSubject())
                .build();
        return dto;
    }

    default Survey_analysis_DTO TransferEntityToDTO_kafka(Survey_analysis survey_analysis) {
        Survey_analysis_DTO dto = Survey_analysis_DTO.builder()
                .type(survey_analysis.getType())
                .subject(survey_analysis.getSubject())
                .build();
        return dto;
    }


}

