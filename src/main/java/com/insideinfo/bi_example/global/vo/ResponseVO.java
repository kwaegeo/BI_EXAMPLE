package com.insideinfo.bi_example.global.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseVO {

    private String code;

    private String msg;

    private List<FolderVO> folderList;
}
