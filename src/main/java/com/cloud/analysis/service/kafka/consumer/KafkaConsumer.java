package com.cloud.analysis.service.kafka.consumer;
import com.cloud.analysis.client.VulgarismServiceClient;
import com.cloud.analysis.dto.User.UserDTO;
import com.cloud.analysis.service.AnalysisService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class KafkaConsumer {

    @Autowired
    AnalysisService analysisService;

    @Autowired
    VulgarismServiceClient vulgarismServiceClient;

    Map<String,Object>  message;

    //현재 kafkaTest를 위한 test topic 설정 -> 추후 auth-topic 변동 예정
    @KafkaListener(topics = "ANSWER_ANALYSIS", groupId = "survey_platform")
    public void consume(Map<String,Object> message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(String.format("ANSWER_ANALYSIS: %s", message));
        this.message = message;
        List<Map<String,Object>> answerDataList = (List<Map<String,Object>>)message.get("answer_data_list");
        List<UserDTO> answerUserList = objectMapper.convertValue(message.get("answer_user_list"), new TypeReference<List<UserDTO>>() {});

        analysisService.AgeUpdate(answerUserList);
        analysisService.JobUpdate(answerUserList);
        analysisService.GenderUpdate(answerUserList);
        analysisService.TimeUpdate(answerDataList);
    }

    @KafkaListener(topics = "SURVEY_REG", groupId = "survey_platform")
    public void consume_survey(Map<String,Object> message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String,Object>> question_list = (List<Map<String,Object>>)message.get("question_List");
        Map<String,Object> survey_info = (Map<String,Object>)message.get("survey_info");

        System.out.println(String.format("SURVEY_REG: %s", message));
        System.out.println(String.format("question_List: %s", question_list));
        System.out.println(String.format("survey_info: %s", survey_info));


        analysisService.InsertSurveyAnalysisOption(question_list,(Integer)survey_info.get("surId"));
        analysisService.SurveyCntUpdate(survey_info);
        analysisService.SurveyCategoryUpdate(survey_info);


        Map<String, Object> Data = analysisService.CheckVulgarism(survey_info,question_list);
        analysisService.InsertVulgarism((Integer) Data.get("surId"), (boolean)Data.get("InfoYn"), (boolean)Data.get("QuestionYn"));

    }

    public Map<String,Object> getMessage(){
        return this.message;
    }
}

