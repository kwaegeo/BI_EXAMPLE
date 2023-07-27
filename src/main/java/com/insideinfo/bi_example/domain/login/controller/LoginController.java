package com.insideinfo.bi_example.domain.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insideinfo.bi_example.domain.login.vo.FoldersVO;
import com.insideinfo.bi_example.domain.login.service.LoginService;
import com.insideinfo.bi_example.domain.login.vo.loginVO;
import com.insideinfo.bi_example.global.mstr.auth.MstrSession;
import com.microstrategy.web.objects.WebObjectsException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
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
    @PostMapping("/login/loginProcess")
    @ResponseBody
    public String loginProcess(@RequestBody loginVO loginVO, HttpServletRequest request) throws JsonProcessingException {


        /**
         * 1. 만약 세션이 존재한다면? 이 페이지로 오지 않고 메인으로 가게 끔 redirect
         *
         * 1-1. REST_API로 한다면 AuthToken을 사용
         *
         * 1-2. WEB_API SDK를 사용한다면.. 어떻게 해야할까 URL 적용시키기가 조금 힘들다.
         *
         * **/

        System.out.println(loginVO.getLoginId());
        System.out.println(loginVO.getLoginPassword());


        return "S00";

    }


    @GetMapping("/login/main")
    public String getMainPage(HttpServletRequest httpServletRequest, Model model){
        HttpSession httpSession = httpServletRequest.getSession();
        String a = (String) httpSession.getAttribute("X-MSTR-TOKEN");
        String b = (String) httpSession.getAttribute("Cookie");

        System.out.println(a);
        System.out.println(b);


        String apiUrl = "http://192.168.70.245:8090/MicroStrategyLibrary/api/folders/";

        System.out.println("요청 URL: " + apiUrl);

        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-MSTR-AuthToken", (String) httpSession.getAttribute("X-MSTR-TOKEN"));
        httpHeaders.set(HttpHeaders.COOKIE, (String) httpSession.getAttribute("Cookie"));
        httpHeaders.set("X-MSTR-ProjectID", "B19DEDCC11D4E0EFC000EB9495D0F44F");

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);


        ResponseEntity<List<FoldersVO>> response = new RestTemplate().exchange(
                apiUrl,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<FoldersVO>>() {
                }
        );
        List<FoldersVO> folderList = response.getBody();

        System.out.println(folderList);
        model.addAttribute("folderList", folderList);
        return "/index";
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
    public String login(@RequestBody Map<String, String> loginMap, Model model, HttpServletRequest request) throws JsonProcessingException {


        Map<String,String> mstrAuthInfo = loginService.login(loginMap);


        /**
         * 1. Http 세션에 MSTR Token, Cookie, SessionID 넣은 뒤 return
         * **/


        HttpSession session = request.getSession(true);
        session.setAttribute("X-MSTR-TOKEN", mstrAuthInfo.get("token"));
        session.setAttribute("Cookie", mstrAuthInfo.get("cookies"));

        return "S00";

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
    @GetMapping("/login/login2")
    public String login(Model model) throws JsonProcessingException {

        Map<String,String> loginMap = new HashMap<>();
        Map<String,String> mstrAuthInfo = loginService.login(loginMap);

        List<FoldersVO> folderList = loginService.getFolderList(mstrAuthInfo);


        for (FoldersVO folder: folderList) {
            System.out.println(folder);
        }

        model.addAttribute("folderList", folderList);

        return "/index";

    }

    @GetMapping("/test123")
    public String test123(HttpServletRequest httpServletRequest, Model model) throws WebObjectsException {

        String aa = httpServletRequest.getRemoteAddr();

        System.out.println(aa);

        MstrSession samplesession = new MstrSession();
        
        String mstrSessionId = samplesession.createSession("z","z",aa);

        System.out.println("감이안온다");
        System.out.println(mstrSessionId);

        model.addAttribute("mstrSessionId", mstrSessionId);
        return "/test123";

    }



}




