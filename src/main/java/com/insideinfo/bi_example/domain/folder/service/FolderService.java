package com.insideinfo.bi_example.domain.folder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insideinfo.bi_example.domain.login.vo.FoldersVO;
import com.insideinfo.bi_example.global.mstr.auth.MstrAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FolderService {

    @Autowired
    private MstrAuth mstrAuth;




}


//세션이 만료되었는지 혹은 유효한지 체크하는 함수 필요

