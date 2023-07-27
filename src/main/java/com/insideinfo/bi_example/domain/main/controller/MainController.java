package com.insideinfo.bi_example.domain.main.controller;

import com.insideinfo.bi_example.domain.login.vo.FoldersVO;
import com.insideinfo.bi_example.domain.main.service.MainService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/main")
    public String MainPage(HttpServletRequest httpServletRequest, Model model){
        mainService.loginProcess();
        return "/index";
    }


}
