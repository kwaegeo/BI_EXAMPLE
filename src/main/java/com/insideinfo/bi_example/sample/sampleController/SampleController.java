package com.insideinfo.bi_example.sample.sampleController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insideinfo.bi_example.sample.sampleService.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/sample")
    public String sampleMain() throws JsonProcessingException, InterruptedException {

        Map<String, String> myMap = sampleService.getAuthToken();
        System.out.println(myMap);
        sampleService.getReports(myMap);

        return "/index";
    }

    @GetMapping("/test3")
    public String test1(){
        return "/semi_structured/test3";
    }

    @GetMapping("/test2")
    public String test2(){
        return "/structured/test2";
    }

    @GetMapping("/test1")
    public String test3(){
        return "/unstructured/test1";
    }

    @GetMapping("/test4")
    public String test4() {return "/login/loginPage"; }
}
