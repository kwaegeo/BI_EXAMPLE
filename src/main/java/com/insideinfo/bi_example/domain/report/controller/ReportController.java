package com.insideinfo.bi_example.domain.report.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/report")
public class ReportController {

    @GetMapping("/info")
    public String getReportInfo(@RequestParam(name = "reportId") String reportId, HttpServletRequest httpServletRequest){
        return"/";
    }

}
