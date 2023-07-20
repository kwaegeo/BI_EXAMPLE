package com.insideinfo.bi_example.sample.sampleService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insideinfo.bi_example.sample.sampleVO.SampleVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SampleService {

    @Value("${BASEIP}")
    private String BASEURL;

    @Value("${BASEIP}")
    private String BASEIP;

    @Value("${BASEPORT}")
    private String BASEPORT;

    @Value("${USERNAME}")
    private String USERNAME;

    @Value("${PASSWORD}")
    private String PASSWORD;

    @Value("${PROJECTID}")
    private String PROJECTID;

    public Map<String, String> getAuthToken() throws JsonProcessingException {

        String b = "http://192.168.70.245:8080/MicroStrategyLibrary/api/auth/login";
        System.out.println(b);

        RestTemplate restTemplate = new RestTemplate();
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);


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
                b,
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


    public void getReports(Map<String, String> myMap) throws JsonProcessingException {

        String b = "http://192.168.70.245:8080/MicroStrategyLibrary/api/folders/78AAF4654A62E9D384D0E094BE585507";
        System.out.println(b);
        System.out.println(myMap);
        System.out.println("bndanbiadbniabn");
        System.out.println(myMap.get("cookies"));
        RestTemplate restTemplate = new RestTemplate();
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-MSTR-AuthToken", myMap.get("token"));
        httpHeaders.set(HttpHeaders.COOKIE, myMap.get("cookies"));
        httpHeaders.set("X-MSTR-ProjectID", "B19DEDCC11D4E0EFC000EB9495D0F44F");

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<SampleVO>> response = restTemplate.exchange(
                b,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<SampleVO>>() {
                }
        );
        List<SampleVO> reports = response.getBody();

        System.out.println(reports);


    }



}
