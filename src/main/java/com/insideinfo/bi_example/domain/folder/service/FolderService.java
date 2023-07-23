package com.insideinfo.bi_example.domain.folder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insideinfo.bi_example.global.mstr.auth.MstrAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FolderService {

    @Autowired
    private MstrAuth mstrAuth;

    public Map<String, String> getFolderInfo(Map<String, String> loginMap) throws JsonProcessingException {

        Map<String, String> mstrInfoMap = mstrAuth.getAuthToken(loginMap);

        return mstrInfoMap;
    }


}
