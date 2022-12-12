package com.cloud.analysis.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.json.simple.JSONObject;

@FeignClient(name = "vulgarism-service", configuration = ConfigVulgarism.class, url = "210.109.61.78:5000")
public interface VulgarismServiceClient {
    @PostMapping(value = "/chk",consumes = "application/json", produces = "application/json")
    String request(@RequestBody JSONObject jsonObject);
}
