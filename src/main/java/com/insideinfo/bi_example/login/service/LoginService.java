package com.insideinfo.bi_example.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insideinfo.bi_example.login.vo.FoldersVO;
import com.insideinfo.bi_example.mstr.auth.MstrAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 파일명 		: LoginService.java
 * 작성일자		: 2023.07.20
 * 작성자		: 이도현
 * 설명		: 로그인 요청 처리 서비스
 * 변경이력		: 2023.07.20 최초작성
 * </pre>
 */
@Service
public class LoginService {

    @Autowired
    private MstrAuth mstrAuth;
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
    public Map<String, String> login(Map<String, String> loginMap) throws JsonProcessingException {

        Map<String, String> mstrInfoMap = mstrAuth.getAuthToken(loginMap);

        return mstrInfoMap;
    }


    public List<FoldersVO> getFolderList(Map<String,String> mstrAuthInfo) throws JsonProcessingException {
        List<FoldersVO> folderList = mstrAuth.getFolderList(mstrAuthInfo);

        return folderList;
    }
}
