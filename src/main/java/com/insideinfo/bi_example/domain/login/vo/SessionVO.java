package com.insideinfo.bi_example.domain.login.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionVO {

    private String mstrAuthToken;

    private String cookieString;

}
