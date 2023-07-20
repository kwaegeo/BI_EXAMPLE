package com.insideinfo.bi_example.login.controller;

import com.insideinfo.bi_example.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * <pre>
 * 파일명 		: LoginController.java
 * 작성일자		: 2023.07.20
 * 작성자		: 이도현
 * 설명		: 로그인 요청 처리 컨트롤러
 * 변경이력		: 2023.07.20 최초작성
 * </pre>
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * <pre>
     * 메소스명		: getLoginPage
     * 작성일자		: 2023.07.20
     * 작성자		: 이도현
     * 설명		: 로그인 화면을 조회한다.
     * 변경이력		: 2023.07.20 최초작성
     * </pre>
     * @return String : login/loginPage.mustache 화면
     */
    @GetMapping("/login/loginPage")
    public String getLoginPage(){
        return "/login/login";
    }



    /**
     * <pre>
     * 메소스명		: login
     * 작성일자		: 2023.07.20
     * 작성자		: 이도현
     * 설명		: 로그인 처리 로직
     * 변경이력		: 2023.07.20 최초작성
     * </pre>
     * @param loginMap : HTTP 요청 Body
     * @return ModelAndView : login/loginPage.jsp 화면
     */
    @PostMapping("/login/login")
    @ResponseBody
    public String login(@RequestBody Map<String, String> loginMap){

        loginService.

        return "/login/login";
    }


}
