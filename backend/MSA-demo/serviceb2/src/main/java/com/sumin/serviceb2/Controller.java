package com.sumin.serviceb2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/response")
    public String checkState(){
        return "Service-B : inst002 정상";
    }
}
