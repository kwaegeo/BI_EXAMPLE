package com.insideinfo.bi_example.global.mstr.auth;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insideinfo.bi_example.domain.login.vo.FoldersVO;
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

    public Map<String, String> getAuthToken(Map<String, String> loginMap) throws JsonProcessingException {

        String apiUrl = "http://" + BASEIP + ":" + BASEPORT+ BASEURL + "auth/login";

        System.out.println("요청 URL: " + apiUrl);

        //header 생성
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        //body 값 추가
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "administrator");
        requestBody.put("password", "");
        requestBody.put("loginMode", "1");
        requestBody.put("maxSearch", "3");
        requestBody.put("workingSet", "10");
        requestBody.put("applicationType", "35");

        // Convert the Map to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBodyJson = objectMapper.writeValueAsString(requestBody);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBodyJson, httpHeaders);

        ResponseEntity<String> response = new RestTemplate().exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        String mstrAuthToken = response.getHeaders().getFirst("X-MSTR-AuthToken");

        List<String> cookieList = response.getHeaders().get(HttpHeaders.SET_COOKIE);

        System.out.println("내가 만든 쿠키~~");
        System.out.println(cookieList);

        String cookiesAsString = String.join("; ", cookieList);
        Map<String, String> maps = new HashMap<>();

        maps.put("token", mstrAuthToken);
        maps.put("cookies", cookiesAsString);

        return maps;
    }

    public List<FoldersVO> getFolderList(Map<String, String> mstrAuthInfo) throws JsonProcessingException {
        String apiUrl = "http://" + BASEIP + ":" + BASEPORT+ BASEURL + "folders";

        System.out.println("요청 URL: " + apiUrl);

        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-MSTR-AuthToken", mstrAuthInfo.get("token"));
        httpHeaders.set(HttpHeaders.COOKIE, mstrAuthInfo.get("cookies"));
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

        return folderList;
    }

    public List<FoldersVO> getFolderInfo(Map<String, String> mstrAuthInfo) throws JsonProcessingException {
        String apiUrl = "http://" + BASEIP + ":" + BASEPORT+ BASEURL + "folders";

        System.out.println("요청 URL: " + apiUrl);

        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-MSTR-AuthToken", mstrAuthInfo.get("token"));
        httpHeaders.set(HttpHeaders.COOKIE, mstrAuthInfo.get("cookies"));
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

        return folderList;
    }

}
