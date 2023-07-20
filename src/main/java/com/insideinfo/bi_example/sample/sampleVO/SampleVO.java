package com.insideinfo.bi_example.sample.sampleVO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Data
public class SampleVO {
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

    public static class OwnerVO {
        private String name;
        private String id;

        // Constructor, getters, and setters...
    }

    public static class CertifiedInfoVO {
        private boolean certified;

        // Constructor, getters, and setters...
    }

    // Constructor, getters, and setters...
}