package com.cloud.analysis.repository;

import com.cloud.analysis.entity.Platform_analysis_option;
import com.cloud.analysis.entity.Survey_analysis;
import com.cloud.analysis.entity.Survey_analysis_option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyAnalysisRepository extends JpaRepository<Survey_analysis, Integer> {

    // 설문 응답자 연령별 비율
    @Query("select distinct SAO from Survey_analysis SA join Survey_analysis_option SAO on SA.analysisId = SAO.analysisId where SAO.analysisId = :analysisId and SAO.surveyId = :surveyId")
    List<Survey_analysis_option> findSurveyAnalysisOptionByColumn(Integer analysisId, Integer surveyId);

    @Query("select SA from Survey_analysis SA join Survey_analysis_option SAO on SA.analysisId = SAO.analysisId where SA.analysisId = :analysisId and SA.surveyId = :surveyId")
    List<Survey_analysis> findSurveyAnalysisByColumn(Integer analysisId, Integer surveyId);

    @Query("select PAO from Platform_analysis PA join Platform_analysis_option PAO on PA.platformAnalysisId = PAO.platformAnalysisId.platformAnalysisId where PA.platformAnalysisId = :platformAnalysisId")
    List<Platform_analysis_option> findPlatformAnalysisByColumn(Integer platformAnalysisId);

    @Query("select SA from Survey_analysis SA where SA.surveyId = :surveyId")
    List<Survey_analysis> findSurveyAnalysisById(Integer surveyId);

    @Query("select SAO from Survey_analysis_option SAO where SAO.analysisId.analysisId = :analysisId")
    List<Survey_analysis_option> findSurveyAnalysisOptionById(Integer analysisId);
}
