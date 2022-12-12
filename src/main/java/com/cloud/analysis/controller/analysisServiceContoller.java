package com.cloud.analysis.controller;

import com.cloud.analysis.entity.Platform_analysis_option;
import com.cloud.analysis.entity.Survey_analysis;
import com.cloud.analysis.entity.Survey_analysis_option;
import com.cloud.analysis.service.AnalysisService;
import com.cloud.analysis.service.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="v1/analysis")
@RequiredArgsConstructor
public class analysisServiceContoller {
    @Autowired
    private AnalysisService analysisService;

    private final KafkaProducer producer;

    // 설문조사 피설문자 통계 조회
    @RequestMapping(value = "/survey_analysis", method = RequestMethod.POST)
    public Map<Object,Object> getSurveyAnalysisList(@RequestParam (value = "analysis_Id") int analysisId, @RequestParam (value = "sur_id") int surveyId) {
        Map<Object,Object> allSurveyAnalysisOptionList = analysisService.getSurveyAnalysisOptionList(analysisId,surveyId);

        return allSurveyAnalysisOptionList;
    }

    // 플랫폼 통계 조회
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/platform_analysis", method = RequestMethod.POST)
    public List<Platform_analysis_option> getPlatformAnalysisList(@RequestParam (value = "platform_analysis_Id") int platformAnalysisId) {
        List<Platform_analysis_option> allPlatformAnalysisList = analysisService.getPlatformAnalysisList(platformAnalysisId);
        return allPlatformAnalysisList;
    }

    @PostMapping("/kafkaConnTest")
    public String sendMessage(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
        return "success";
    }
    @RequestMapping(value = "/survey_analysis_info", method = RequestMethod.GET)
    public List<Object> getTypeSubjectList(@RequestParam (value = "sur_id") int surId) {
        List<Object> allTypeSubjectList = analysisService.getTypeSubjectList(surId);
        return allTypeSubjectList;
    }

//    @RequestMapping(value="/answerTopicTest",method=RequestMethod.GET)
//    public Map<String,Object> getTopicData(){
//        Map<String,Object> Data = analysisService.Test();
//        return Data;
//    }

}
