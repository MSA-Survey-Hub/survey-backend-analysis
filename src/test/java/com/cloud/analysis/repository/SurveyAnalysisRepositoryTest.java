//package com.cloud.analysis.repository;
//import com.cloud.analysis.client.VulgarismServiceClient;
//import com.cloud.analysis.entity.Survey_analysis;
////import com.cloud.analysis.entity.enums.TableType;
//import com.cloud.analysis.service.AnalysisService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.json.simple.JSONObject;
//import java.util.List;
//
//@SpringBootTest
//class SurveyAnalysisRepositoryTest {
//    @Autowired
//    SurveyAnalysisRepository surveyAnalysisRepository;
//
//    @Autowired
//    private AnalysisService analysisService;
//
//    @Autowired
//    private VulgarismServiceClient vulgarismServiceClient;
//
////    @Autowired
////    private Vulgarism_DTO vulgarism_dto;
//
//    @Test
//    void read() {
//        List<Survey_analysis> analysisList = surveyAnalysisRepository.findAll();
//        analysisList.forEach(analysis -> {
//            System.out.println("analysis.toString() = " + analysis.toString());
//        });
//    }
//
////    @Test
////    public List<Survey_analysis> getTypeSubjectList(Integer analysisId) {
////        List<Survey_analysis> TypeSubjectList = analysisRepository.findTypeSubjectById(analysisId);
////        System.out.println("analysis.toString() = " + analysis.toString());
////        return TypeSubjectList;
////    }
//
//    @Test
//    void Test(){
////        Vulgarism_DTO vulgarism_dto = new Vulgarism_DTO();
////        vulgarism_dto.setText("씨발");
////        System.out.println("vulgarism_dto = " + vulgarism_dto);
//
//        JSONObject jo = new JSONObject();
//        jo.put("text","씨발");
//        System.out.println("jo.toString() = " + jo);
//
//        String test = vulgarismServiceClient.request(jo);
//        System.out.println("test = " + test);
//
////        VulgarismCheckClient callService = Feign.builder()
////                .encoder(new Encoder.Default())
////                .decoder(new Decoder.Default())
////                .requestInterceptor(new ConfigVulgarism().getJwtRequestInterceptor())
////                .target(VulgarismCheckClient.class, "http://localhost:8710");
//    }
//
//}
