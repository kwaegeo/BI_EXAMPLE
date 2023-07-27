package com.insideinfo.bi_example.domain.main.service;

import com.insideinfo.bi_example.domain.login.vo.SessionVO;
import com.insideinfo.bi_example.global.mstr.auth.MstrAuth;
import com.insideinfo.bi_example.global.mstr.folder.MstrFolder;
import com.insideinfo.bi_example.global.vo.FolderVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    private MstrFolder mstrFolder;

    public List<FolderVO> getFolderList(SessionVO sessionInfo){
        List<FolderVO> folderList = mstrFolder.getFolderList(sessionInfo);

        return folderList;
    }

}
