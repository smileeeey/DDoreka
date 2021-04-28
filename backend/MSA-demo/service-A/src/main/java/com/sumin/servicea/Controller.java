package com.sumin.servicea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/apiA")
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_B_NAME = "SERVICE-B";

    @GetMapping("/test")
    public String test(){
        return "test success";
    }
    @GetMapping("/request")
    public String callServiceB(){
        ResponseEntity<String> ackMessage;
        String apiPath = "/api/response";

        ackMessage = restTemplate.getForEntity("http://"+SERVICE_B_NAME + apiPath, String.class);

        return "Service-A: inst001 에서 호출" + ">" + ackMessage.getBody();
    }
}
