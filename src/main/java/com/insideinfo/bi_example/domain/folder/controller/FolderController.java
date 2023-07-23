package com.insideinfo.bi_example.domain.folder.controller;

import com.insideinfo.bi_example.domain.folder.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FolderController {

    @Autowired
    private FolderService folderService;


    @GetMapping("/folders")
    @ResponseBody
    public String getFolderInfo(@RequestParam(name = "folderId") String folderId){

        System.out.println(folderId);
        return "z";
    }
}
