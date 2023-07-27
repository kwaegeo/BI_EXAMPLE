package com.insideinfo.bi_example.domain.folder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insideinfo.bi_example.domain.folder.service.FolderService;
import com.insideinfo.bi_example.domain.login.vo.FoldersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class FolderController {

    @Autowired
    private FolderService folderService;


    @GetMapping("/folders")
    @ResponseBody
    public String getFolderInfo(@RequestParam(name = "folderId") String folderId) throws JsonProcessingException {


        System.out.println(folderId);
        return "z";
    }
}
