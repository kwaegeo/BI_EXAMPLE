package com.insideinfo.bi_example.global.mstr.auth;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insideinfo.bi_example.global.vo.FolderVO;
import com.insideinfo.bi_example.domain.login.vo.LoginVO;
import com.insideinfo.bi_example.domain.login.vo.SessionVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class MstrAuth {

    @Value("${BASEURL}")
    private String BASEURL;

    @Value("${BASEIP}")
    private String BASEIP;

    @Value("${BASEPORT}")
    private String BASEPORT;

    @Value("${PROJECTID}")
    private String PROJECTID;

    public SessionVO login(LoginVO loginVO){

        String mstrApiUrl = "http://" + BASEIP + ":" + BASEPORT+ BASEURL + "auth/login";

        System.out.println("요청 URL: " + mstrApiUrl);

        //header 생성
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        //body 값 추가
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", loginVO.getLoginId());
        requestBody.put("password", loginVO.getLoginPassword());
        requestBody.put("loginMode", "1");
        requestBody.put("maxSearch", "3");
        requestBody.put("workingSet", "10");
        requestBody.put("applicationType", "35");
        requestBody.put("metadataLocale", "ko_KR");
        requestBody.put("warehouseDataLocale", "ko_KR");
        requestBody.put("displayLocale", "ko_KR");
        requestBody.put("messagesLocale", "ko_KR");
        requestBody.put("numberLocale", "ko_KR");
        requestBody.put("timeZone", "asia/seoul");

        // Map을 JSON 형태로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBodyJson = "";
        try {
            requestBodyJson = objectMapper.writeValueAsString(requestBody);
        }catch (Exception e){
            e.printStackTrace();
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBodyJson, httpHeaders);

        ResponseEntity<String> response = new RestTemplate().exchange(
                mstrApiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // X-MSTR-Token 추출
        String mstrAuthToken = response.getHeaders().getFirst("X-MSTR-AuthToken");

        // CookieList 추출
        List<String> cookieList = response.getHeaders().get(HttpHeaders.SET_COOKIE);

        // Cookie 하나의 문자열로 결합
        String cookiesAsString = String.join("; ", cookieList);

        System.out.println("내가 만든 쿠키~: " + cookiesAsString);

        // session 정보 return
        SessionVO sessionInfo = SessionVO.builder().
                mstrAuthToken(mstrAuthToken).
                cookieString(cookiesAsString).
                build();

        return sessionInfo;
    }

    public List<FolderVO> getFolderList(Map<String, String> mstrAuthInfo) throws JsonProcessingException {
        String apiUrl = "http://" + BASEIP + ":" + BASEPORT+ BASEURL + "folders";

        System.out.println("요청 URL: " + apiUrl);

        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-MSTR-AuthToken", mstrAuthInfo.get("token"));
        httpHeaders.set(HttpHeaders.COOKIE, mstrAuthInfo.get("cookies"));
        httpHeaders.set("X-MSTR-ProjectID", "B19DEDCC11D4E0EFC000EB9495D0F44F");

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);


        ResponseEntity<List<FolderVO>> response = new RestTemplate().exchange(
                apiUrl,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<FolderVO>>() {
                }
        );
        List<FolderVO> folderList = response.getBody();

        return folderList;
    }



}
