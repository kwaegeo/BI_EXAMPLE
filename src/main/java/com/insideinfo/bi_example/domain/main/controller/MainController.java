package com.insideinfo.bi_example.domain.main.controller;

import com.insideinfo.bi_example.domain.login.vo.SessionVO;
import com.insideinfo.bi_example.domain.main.service.MainService;
import com.insideinfo.bi_example.global.vo.FolderVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/main")
    public String getMainPage(HttpServletRequest httpServletRequest, Model model){

        /**
         *
         * Session의 값 체크 후 넘기기
         * **/
        HttpSession httpSession = httpServletRequest.getSession();
        String mstrAuthToken = "";
        SessionVO sessionInfo = null;
        try {
            sessionInfo = (SessionVO) httpSession.getAttribute("sessionInfo");
            mstrAuthToken = sessionInfo.getMstrAuthToken();
        }catch (Exception e){
            return "redirect:/login/loginPage";
        }
        // X-MSTR-TOKEN이 없을 경우 LoginPage로 보냄.
        if (mstrAuthToken == null || mstrAuthToken.isEmpty()) {
            return "redirect:/login/loginPage";
        }

        //TODO: X-MSTR-TOKEN의 값이 있을 경우엔 세션 유효한지 확인하는 API 호출 할 것

        //TODO: 사용자의 Folder 메뉴 기본으로 가져오기
        List<FolderVO> folderList = mainService.getFolderList(sessionInfo);

        model.addAttribute("folderList", folderList);
        return "/index";
    }

}
