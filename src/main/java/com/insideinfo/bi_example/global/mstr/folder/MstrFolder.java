package com.insideinfo.bi_example.global.mstr.folder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insideinfo.bi_example.domain.login.vo.SessionVO;
import com.insideinfo.bi_example.global.vo.FolderVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class MstrFolder {

    @Value("${BASEURL}")
    private String BASEURL;

    @Value("${BASEIP}")
    private String BASEIP;

    @Value("${BASEPORT}")
    private String BASEPORT;

    @Value("${PROJECTID}")
    private String PROJECTID;

    public List<FolderVO> getFolderList(SessionVO sessionInfo){
        
        //일단 공유리포트 폴더로 고정시킴
        String mstrApiUrl = "http://" + BASEIP + ":" + BASEPORT+ BASEURL + "folders/" + "D3C7D461F69C4610AA6BAA5EF51F4125";

        System.out.println("요청 URL: " + mstrApiUrl);

        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-MSTR-AuthToken", sessionInfo.getMstrAuthToken());
        httpHeaders.set(HttpHeaders.COOKIE, sessionInfo.getCookieString());
        httpHeaders.set("X-MSTR-ProjectID", PROJECTID);

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<FolderVO>> response = new RestTemplate().exchange(
                mstrApiUrl,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<FolderVO>>() {
                }
        );
        List<FolderVO> folderList = response.getBody();

        return folderList;
    }

    public List<FolderVO> getSubList(String folderId, SessionVO sessionInfo){

        String mstrApiUrl = "http://" + BASEIP + ":" + BASEPORT+ BASEURL + "folders/" + folderId;

        System.out.println("요청 URL: " + mstrApiUrl);

        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-MSTR-AuthToken", sessionInfo.getMstrAuthToken());
        httpHeaders.set(HttpHeaders.COOKIE, sessionInfo.getCookieString());
        httpHeaders.set("X-MSTR-ProjectID", PROJECTID);

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<FolderVO>> response = new RestTemplate().exchange(
                mstrApiUrl,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<FolderVO>>() {
                }
        );
        List<FolderVO> folderList = response.getBody();

        return folderList;

    }
}
