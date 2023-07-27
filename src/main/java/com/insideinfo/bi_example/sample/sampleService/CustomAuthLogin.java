package com.insideinfo.bi_example.sample.sampleService;

import com.microstrategy.utils.StringUtils;
import com.microstrategy.web.admin.beans.AdminServersHelper;
import com.microstrategy.web.app.AbstractExternalSecurity;
import com.microstrategy.web.app.ExternalSecurity;
import com.microstrategy.web.beans.RequestKeys;
import com.microstrategy.web.platform.ContainerServices;
import com.microstrategy.web.preferences.Preferences;
import com.microstrategy.web.preferences.PreferencesException;
import com.microstrategy.web.preferences.PreferencesMgr;

/**
 * 커스텀 로그인 처리하는 클래스인데
 * */
public class CustomAuthLogin extends AbstractExternalSecurity {

    // * 부울 변수는 "먼저 로그인한 후 프로젝트 표시" 옵션의 선택 여부를 나타냅니다 라고 하긴하네
    private boolean isLoginFirst = false;

    public int handlesAuthenticationRequest(RequestKeys reqKeys, ContainerServices cntSvcs, int reason){
        isLoginFirst = isLoginFirstEnabled();

        /*
         * This is a hidden field sent from the custom login page. It is not null means finished custom login.
         */
        String loginPageRequestKey = reqKeys.getValue("loginPage");
        System.out.println("CustomAuthLogin.handlesAuthenticationRequest");

        if (StringUtils.isNotEmpty(loginPageRequestKey)) {
            return USE_MSTR_DEFAULT_LOGIN;
        } else {
            /*
             * If reason is login first, means we will direct to login page for "login first" mode.
             */
            if (reason == ExternalSecurity.LOGIN_FIRST) {
                return USE_CUSTOM_LOGIN_PAGE;
            } else {
                /*
                 * if reason is not login first but we check the "login first, then show the project" option, that means we will direct to other
                 * page instead of custom login page; otherwise we will direct to custom login page.
                 */
                if (isLoginFirst) {
                    return  USE_MSTR_DEFAULT_LOGIN;
                }
                else {
                    return USE_CUSTOM_LOGIN_PAGE;
                }
            }
        }
    }

    /*
     * Return the URL of the custom login page.
     */
    public String getCustomLoginURL(String originalURL, String desiredServer, int desiredPort,String desiredProject) {

        /* The custom login page should have a login form where user can input login credentials.
         * After user submit the login form, the custom login page should create MicroStrategy
         * Web IServer Session, redirect to MicroStrategy page and have the session manager state in
         * the URL using "usrSmgr" parameter.
         * put isLoginFirst as a request parameter
         */

        System.out.println("CustomAuthLogin.getCustomLoginURL");
        System.out.println("======||=======");


        return "../plugins/LoginPlugIn/jsp/customlogin.jsp?LoginFirst=" + (isLoginFirst?"1":"0");
    }

    /*
     * Override getFailureURL(int, com.microstrategy.web.platform.ContainerServices)
     */
    public String getFailureURL(int reqType, ContainerServices cntrSvcs) {
        System.out.println("CustomAuthLogin.getFailureURL");
        //return getCustomLoginURL(null, null, 0, null);
        return "../plugins/LoginPlugIn/jsp/customlogin.jsp?LoginFirst=" + (isLoginFirst?"1":"0")+"&errorMsg=1";
    }

    /*
     * checks login first preference.
     */
    private static boolean isLoginFirstEnabled() {
        String prefValue = getDefaultPreference(AdminServersHelper.PROPERTY_PROJECT_LIST_LOGIN_REQUIRED);

        return (prefValue != null && prefValue.equals(AdminServersHelper.PROPERTY_VALUE_YES));
    }

    //개킹받는다 진짜

    private static String getDefaultPreference(String key) {
        String __result = null;
        Preferences prefs = null;

        try {
            prefs = PreferencesMgr.getInstance().getSysDefaultPreferences();
        } catch (PreferencesException ex) {}

        __result = (prefs != null) ? prefs.getValue(key) : null;
        return __result;
    }
}
