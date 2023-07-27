package com.insideinfo.bi_example.global.utils;

import jakarta.servlet.http.HttpServletRequest;

public class HttpUtils {

    public static String getRemoteAddr(HttpServletRequest request) {

        String remoteAddr = "";				// L7에서 넘겨주는 Client IP Header Info

        remoteAddr = request.getHeader("X-Forwarded-For");

        if(remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)){
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }

        if(remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)){
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }

        if(remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)){
            remoteAddr = request.getHeader("HTTP_CLIENT_IP");
        }

        if(remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)){
            remoteAddr = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if(remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)){
            remoteAddr = request.getHeader("CLIENT-IP");
        }

        if(remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)){
            remoteAddr = request.getRemoteAddr();
        }

        return remoteAddr;
    }


}
