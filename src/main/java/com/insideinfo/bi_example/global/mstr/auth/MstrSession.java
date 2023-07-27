package com.insideinfo.bi_example.global.mstr.auth;

import com.microstrategy.web.objects.WebIServerSession;
import com.microstrategy.web.objects.WebObjectInfo;
import com.microstrategy.web.objects.WebObjectsException;
import com.microstrategy.web.objects.WebObjectsFactory;
import com.microstrategy.webapi.EnumDSSXMLApplicationType;
import com.microstrategy.webapi.EnumDSSXMLAuthModes;
import com.microstrategy.webapi.EnumDSSXMLFolderNames;

public class MstrSession {


    protected WebObjectsFactory factory		= WebObjectsFactory.getInstance();

    public WebIServerSession serverSession;
    private String mstrUserId;
    private String mstrProfileId;

    /**
     * <pre>
     * 메소스명		: createSession
     * 작성일자		: 2021.04.15
     * 작성자		: 송동근
     * 설명		: MSTR 세션을 생성한다.
     * 변경이력		: 2021.04.15 최초작성
     * </pre>
     *
     * @param userId   : 아이디
     * @param userPw   : 비밀번호
     * @param clientId : 클라이언트 IP
     * @return : 세션 ID
     */
    public String createSession(String userId, String userPw, String clientId) throws WebObjectsException {

        String sessionId = "";
        serverSession = factory.getIServerSession();

        String iServerName = null;

        // config.xml에서 설정한 서버 이름을 가져온다.
        iServerName = "inside";

        String sProjectName = "MicroStrategy Tutorial";

        // 세션 생성
        serverSession.setServerName(iServerName);
        serverSession.setServerPort(0);
        serverSession.setProjectName(sProjectName);
        serverSession.setLogin("administrator");
        serverSession.setPassword("");
        serverSession.setLocaleID(1042); // 한국어
        serverSession.setAuthMode(EnumDSSXMLAuthModes.DssXmlAuthStandard);
        // serverSession.setApplicationType(EnumDSSXMLApplicationType.DssXmlApplicationCustomApp);
        serverSession.setApplicationType(EnumDSSXMLApplicationType.DssXmlApplicationDSSWeb);
        serverSession.setClientID(clientId);
        serverSession.getSessionID();

//        System.out.println(serverSession.getSessionID());


        // 세션정보로 사용자 개체 ID 추출
        this.mstrUserId = serverSession.getUserInfo(true).getID();
        this.mstrProfileId = factory.getObjectSource()
                .getFolderID(EnumDSSXMLFolderNames.DssXmlFolderNameProfileReports);

        // 세션 상태 추출
        sessionId = serverSession.saveState(0);

        WebObjectInfo what = serverSession.getUserInfo();
        System.out.println("음");
        return sessionId;
    }

}
