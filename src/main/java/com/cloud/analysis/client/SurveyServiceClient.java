package com.cloud.analysis.client;

import com.cloud.analysis.dto.Vulgarism_DTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "survey-service",url="localhost:9000/survey-service", configuration = Config.class)
public interface SurveyServiceClient {
    @PostMapping(value = "/v1/survey/vulgarismInsert")
    void vulgarismInsert(@RequestBody Vulgarism_DTO vulgarismDTO);
}
