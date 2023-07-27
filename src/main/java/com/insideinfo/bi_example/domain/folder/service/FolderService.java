package com.insideinfo.bi_example.domain.folder.service;

import com.insideinfo.bi_example.domain.login.vo.SessionVO;
import com.insideinfo.bi_example.global.mstr.auth.MstrAuth;
import com.insideinfo.bi_example.global.mstr.folder.MstrFolder;
import com.insideinfo.bi_example.global.vo.FolderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {

    @Autowired
    private MstrFolder mstrFolder;


    public List<FolderVO> getSubList(String folderId, SessionVO sessionInfo){

        List<FolderVO> folderList = mstrFolder.getSubList(folderId, sessionInfo);
        return folderList;
    }

}


//세션이 만료되었는지 혹은 유효한지 체크하는 함수 필요

