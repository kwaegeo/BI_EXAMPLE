package com.insideinfo.bi_example.domain.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insideinfo.bi_example.global.vo.FolderVO;
import com.insideinfo.bi_example.domain.login.vo.LoginVO;
import com.insideinfo.bi_example.domain.login.vo.SessionVO;
import com.insideinfo.bi_example.global.mstr.auth.MstrAuth;
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
     * @param LoginVO : HTTP 요청 Body
     * @return ModelAndView : login/loginPage.jsp 화면
     */
    public SessionVO login(LoginVO loginVO) {

        SessionVO sessionInfo = mstrAuth.login(loginVO);

        return sessionInfo;
    }





















    public List<FolderVO> getFolderList(Map<String,String> mstrAuthInfo) throws JsonProcessingException {
        List<FolderVO> folderList = mstrAuth.getFolderList(mstrAuthInfo);

        return folderList;
    }
}
