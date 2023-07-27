package com.insideinfo.bi_example.domain.folder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insideinfo.bi_example.domain.folder.service.FolderService;
import com.insideinfo.bi_example.domain.login.vo.SessionVO;
import com.insideinfo.bi_example.global.vo.FolderVO;
import com.insideinfo.bi_example.global.vo.ResponseVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/folder")
public class FolderController {

    @Autowired
    private FolderService folderService;


    @GetMapping("/subList")
    @ResponseBody
    public ResponseVO getSubList(@RequestParam(name = "folderId") String folderId, HttpServletRequest httpServletRequest) {

        HttpSession httpSession = httpServletRequest.getSession();
        SessionVO sessionInfo = (SessionVO) httpSession.getAttribute("sessionInfo");

        List<FolderVO> folderList = folderService.getSubList(folderId, sessionInfo);

        ResponseVO response = ResponseVO.builder().
                code("S00").
                msg("어우졸려").
                folderList(folderList).
                build();

        return response;
    }
}
