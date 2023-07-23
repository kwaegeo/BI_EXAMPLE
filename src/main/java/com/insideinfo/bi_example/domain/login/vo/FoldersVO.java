package com.insideinfo.bi_example.domain.login.vo;

import com.insideinfo.bi_example.sample.sampleVO.SampleVO;
import lombok.Data;

import java.time.Instant;


@Data
public class FoldersVO {
    private String name;
    private String id;
    private int type;
    private String description;
    private int subtype;
    private Instant dateCreated;
    private Instant dateModified;
    private String version;
    private int acg;
    private OwnerVO owner;
    private int extType;
    private int viewMedia;
    private CertifiedInfoVO certifiedInfo;

    // Constructor, getters, and setters...

    // Define inner classes for nested objects

    @Data
    public static class OwnerVO {
        private String name;
        private String id;

        // Constructor, getters, and setters...
    }

    @Data
    public static class CertifiedInfoVO {
        private boolean certified;

        // Constructor, getters, and setters...
    }

    // Constructor, getters, and setters...
}
