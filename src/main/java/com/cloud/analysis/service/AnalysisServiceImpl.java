package com.cloud.analysis.service;

import com.cloud.analysis.client.SurveyServiceClient;
import com.cloud.analysis.client.VulgarismServiceClient;
import com.cloud.analysis.dto.Survey_analysis_option_DTO;
import com.cloud.analysis.dto.User.UserDTO;
import com.cloud.analysis.dto.Vulgarism_DTO;
import com.cloud.analysis.entity.Platform_analysis_option;
import com.cloud.analysis.entity.Survey_analysis;
import com.cloud.analysis.entity.Survey_analysis_option;
import com.cloud.analysis.repository.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private final SurveyAnalysisRepository surveyAnalysisRepository;
    @Autowired
    private final SurveyAnalysisOptionRepository surveyAnalysisOptionRepository;
    @Autowired
    private final PlatformAnalysisRepository platformAnalysisRepository;
    @Autowired
    private final PlatformAnalysisOptionRepository platformAnalysisOptionRepository;

    @Autowired
    private final VulgarismRepository vulgarismRepository;

    @Autowired
    private final SurveyServiceClient surveyServiceClient;

    @Autowired
    VulgarismServiceClient vulgarismServiceClient;

    @Override
    public List<Survey_analysis_option> getSurveyAnalysisList(Integer analysisId,Integer surveyId) {
        List<Survey_analysis_option> surveyAnalysisList = surveyAnalysisRepository.findSurveyAnalysisByColumn(analysisId,surveyId);
        return surveyAnalysisList;
    }

    @Override
    public List<Platform_analysis_option> getPlatformAnalysisList(Integer analysisId) {
        List<Platform_analysis_option> platformAnalysisList = surveyAnalysisRepository.findPlatformAnalysisByColumn(analysisId);
        return platformAnalysisList;
    }

    @Override
    public List<Object> getTypeSubjectList(Integer surveyId) {
        List<Object> Large = new ArrayList<>();
        List<Survey_analysis> TypeSubjectList = surveyAnalysisRepository.findSurveyAnalysisById(surveyId);
        TypeSubjectList.forEach(surveyAnalysis->{
            Map<String, Object> Middle = new HashMap<>();
            Middle.put("type", surveyAnalysis.getType());
            Middle.put("subject", surveyAnalysis.getSubject());

            Integer analysisId = surveyAnalysis.getAnalysisId();
            List<Map<String, Integer>> small = new ArrayList<>();
            List<Survey_analysis_option> OptionList = surveyAnalysisRepository.findSurveyAnalysisOptionById(analysisId);
            OptionList.forEach(surveyAnalysisOption->{
                Map<String, Integer> optionInfo = new HashMap<>();
                optionInfo.put("option_id", surveyAnalysisOption.getOptionId());
                optionInfo.put("value", surveyAnalysisOption.getValue());
                small.add(optionInfo);
            });
            Middle.put("option", small);
            Large.add(Middle);
        });
        return Large;
    }

    @Override
    @Transactional
    public void AgeUpdate(List<UserDTO> answerUserList) {
        System.out.println("answerUserList = " + answerUserList);
        answerUserList.forEach((answerUser)->{
            int age = answerUser.getAge();
            int optionId=0;
            if(age<=10){
                optionId = 1;
            } else if (10<age & age<=20) {
                optionId = 2;
            } else if (20<age & age<=30){
                optionId = 3;
            }else if (30<age & age<=40){
                optionId = 4;
            }
            surveyAnalysisOptionRepository.updateValue(optionId);
        });
    }

    @Override
    @Transactional
    public void GenderUpdate(List<UserDTO> answerUserList) {
        answerUserList.forEach((answerUser)->{
            String gender = answerUser.getGender();
            int optionId=0;
            if(gender.equals("M")){
                optionId = 9;
            } else {
                optionId = 10;
            }
            surveyAnalysisOptionRepository.updateValue(optionId);
        });
    }

    @Override
    @Transactional
    public void JobUpdate(List<UserDTO> answerUserList){
        answerUserList.forEach((answerUser)->{
            String job = answerUser.getJob();
            int optionId=0;
            if(job.equals("FE개발자")){
                optionId = 5;
            } else if(job.equals("BE개발자")) {
                optionId = 6;
            } else if(job.equals("Infra개발자")) {
                optionId = 7;
            } else{
                optionId = 8;
            }
            surveyAnalysisOptionRepository.updateValue(optionId);
        });
    }

    @Override
    @Transient
    public void TimeUpdate(List<Map<String,Object>> answerDataList) {
        System.out.println("answerDataList = " + answerDataList);
        answerDataList.forEach((answerData)->{
            long systemTimeMills = (long)answerData.get("reg_dt");
            LocalDateTime systemLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(systemTimeMills), TimeZone.getDefault().toZoneId());

            int time = systemLocalDateTime.getHour();
            int optionId=0;
            if(6<time & time<=10){
                optionId = 11;
            } else if(10<time & time<=14) {
                optionId = 12;
            } else if(14<time & time<=18) {
                optionId = 13;
            } else if(18<time & time<=24){
                optionId = 14;
            }
            surveyAnalysisOptionRepository.updateValue(optionId);
        });
    }

    @Override
    @Transient
    public void SurveyCntUpdate(Map<String,Object> survey_info){
        String regDtString = survey_info.get("regDt").toString().split("-")[1];
        int regDt = Integer.parseInt(regDtString);
        System.out.println("(survey_info.get(\"regDt\")) = " + regDt);
        int optionId=0;
        if(0<regDt & regDt<=3){
            optionId = 1;
        } else if(3<regDt & regDt<=6) {
            optionId = 2;
        } else if(6<regDt & regDt<=9) {
            optionId = 3;
        } else if(9<regDt & regDt<=12){
            optionId = 4;
        }
        platformAnalysisOptionRepository.updateValue(optionId);
    }

    @Override
    @Transient
    public void SurveyCategoryUpdate(Map<String,Object> survey_info){
        Map<String, Object> surveyCategory = (Map<String, Object>)survey_info.get("surveyCategory");
        String content = surveyCategory.get("content").toString();
        System.out.println("surveyCategory = " + surveyCategory.get("content"));
        int optionId=0;
        if(content.equals("여행")){
            optionId = 9;
        } else if(content.equals("취미")) {
            optionId = 10;
        } else if(content.equals("음식")) {
            optionId = 11;
        } else if(content.equals("연애")){
            optionId = 12;
        }
        platformAnalysisOptionRepository.updateValue(optionId);
    }
    @Override
    @Transient
    public void InsertVulgarism(int surID, boolean InfoYn, boolean QuestionYn){
        Vulgarism_DTO vulgarismDTO = new Vulgarism_DTO().builder()
                        .surId(surID)
                        .infoYn(InfoYn)
                        .questionYn(QuestionYn)
                        .build();

        surveyServiceClient.vulgarismInsert(vulgarismDTO);
    }

    @Override
    public Map<String, Object> CheckVulgarism(Map<String, Object> survey_info, List<Map<String, Object>> question_list){
        boolean InfoYn = false;
        boolean QuestionYn = false;
        //survey_id
        System.out.println("설문 아이디 = " + survey_info.get("surId"));

        //question_yn
        question_list.forEach( question ->
                System.out.println("질문! = " + question.get("content"))
        );

        //info_yn
        System.out.println("설문 제목 = " + survey_info.get("title"));
        System.out.println("설문 설명 = " + survey_info.get("description"));

        JSONObject jo = new JSONObject();
        jo.put("text",survey_info.get("title"));
        String titleYN = vulgarismServiceClient.request(jo);
        jo.put("text",survey_info.get("description"));
        String descriptionYN = vulgarismServiceClient.request(jo);

        if(titleYN.equals("욕") || descriptionYN.equals("욕")){
            InfoYn = true;
        }

        for(int i=0; i<question_list.size(); i++){
            jo.put("text", question_list.get(i).get("content"));
            String YN = vulgarismServiceClient.request(jo);

            if (YN.equals("욕")) {
                QuestionYn = true;
            }
        };

        System.out.println("Info 결과 = " + InfoYn + QuestionYn);
        Map<String, Object> Data = new HashMap<>();
        Data.put("surId",survey_info.get("surId"));
        Data.put("InfoYn",InfoYn);
        Data.put("QuestionYn",QuestionYn);
        System.out.println("Data = " + Data);

        return Data;
    };

    @Override
    public void InsertSurveyAnalysisOption(List<Map<String, Object>> question_list,Integer surId){
        int analysisId = 0;

        for (Map<String,Object> question : question_list) { // 향상된 for문(for-each)
            if(!question.get("questionType").toString().equals("Sub")){
                System.out.println("!!!!!!!!!!!!!!!!!!!!   question.get(\"questionType\").toString() = " + question.get("questionType").toString());
                ++analysisId;
                List<Map<String,Object>> optionList = (List<Map<String,Object>>)question.get("optionList");
                int finalAnalysisId = analysisId;

                Insert_analysis(question,finalAnalysisId,surId);
                optionList.forEach((option)->{
                            System.out.println("> analysisId = " + finalAnalysisId);
                            System.out.println("> optionNumber = " + option.get("optionOrder"));
                            Insert_option(option,finalAnalysisId,surId);

//                            Survey_analysis_option survey_analysis_option = new Survey_analysis_option().builder()
//                                    .optionId((Integer) option.get("optionOrder"))
//                                    .optionName(question.get("content").toString())
//                                    .value(0)
//                                    .analysisId(analysis)
//                                    .surveyId((Integer)question.get("surId"))
//                                    .build();
//                            surveyAnalysisOptionRepository.save(survey_analysis_option);
                        }
                );
            }
        }
    }

    public void Insert_option(Map<String, Object> option,Integer analysis, Integer surId){
        Survey_analysis_option survey_analysis_option = new Survey_analysis_option().builder()
                .optionName(option.get("optionName").toString())
                .value(0)
                .analysisId(analysis)
                .surveyId(surId)
                .build();
        surveyAnalysisOptionRepository.save(survey_analysis_option);
    }

    public void Insert_analysis(Map<String, Object> question,Integer analysis, Integer surId){
        Survey_analysis survey_analysis = new Survey_analysis().builder()
                .analysisId(analysis)
                .subject(question.get("content").toString())
                .surveyId(surId)

//                .subject()
//                .optionName(question.get("content").toString())
//                .value(0)
//                .analysisId(analysis)
//                .surveyId(surId)
                .build();
        surveyAnalysisRepository.save(survey_analysis);
    }
}
