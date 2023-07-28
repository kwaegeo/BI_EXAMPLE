package com.insideinfo.bi_example.sample.sampleController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insideinfo.bi_example.sample.sampleService.SampleService;
import com.microstrategy.utils.serialization.EnumWebPersistableState;
import com.microstrategy.web.objects.WebIServerSession;
import com.microstrategy.web.objects.WebObjectsException;
import com.microstrategy.web.objects.WebObjectsFactory;
import com.microstrategy.webapi.EnumDSSXMLAuthModes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
public class SampleController {

    @Autowired
    private SampleService sampleService;

    private static WebObjectsFactory factory = null;
    private static WebIServerSession serverSession = null;

    @GetMapping("/sample")
    public String sampleMain() throws JsonProcessingException, InterruptedException {

        Map<String, String> myMap = sampleService.getAuthToken();
        System.out.println(myMap);
        sampleService.getReports(myMap);

        return "/index";
    }

    @GetMapping("/test5")
    @ResponseBody
    public String test5(){
        if (serverSession == null) {
            // create factory object
            factory = WebObjectsFactory.getInstance();
            serverSession = factory.getIServerSession();

            // Set up session properties
            serverSession.setServerName("192.168.70.245"); // Should be replaced with the name of an Intelligence Server
            serverSession.setServerPort(0);
            serverSession.setProjectName("MicroStrategy Tutorial"); // Project where session is created
            serverSession.setLocaleID(1042); // 한국어
            serverSession.setAuthMode(1);
            serverSession.setLogin("administrator"); // User ID
            serverSession.setPassword(""); // Password
            // Initialize the session with the above parameters
            try {
                System.out.println("nSession created with ID: "+ serverSession.getSessionID());
                System.out.println("Session State: "+ serverSession.saveState(0));
            } catch (WebObjectsException ex) {
                System.out.println( "Error creating session:" + ex.getMessage());
            }
        }
        // Return session
        StringBuilder urlSB = new StringBuilder();
        urlSB.append("http").append("://").append("192.168.70.245:8090"); //Web Server name and port
        urlSB.append("/MicroStrategy/servlet/mstrWeb");
        urlSB.append("?server=").append("192.168.70.245"); //I Server name
        // urlSB.append("&port=0");
        urlSB.append("&project=").append("MicroStrategy+Tutorial"); // Project name
        urlSB.append("&evt=").append(4001);
        urlSB.append("&reportID=").append("F94F34354152449D3359579FA7A12EF7"); //Report ID
        urlSB.append("&reportViewMode=").append(1);
        urlSB.append("&src=mstrWeb.").append("reportNoHeaderNoFooterNoPath").append(".").append(4001);
        urlSB.append("&usrSmgr=").append(serverSession.saveState(0));

        System.out.println(urlSB.toString()); // Final URL is printed to console.
        System.out.println();

        System.out.println("이거 맞나");

        return "되고있는중인가?";
    }



    @GetMapping("/test3")
    public String test1(){
        return "/semi_structured/test3";
    }

    @GetMapping("/test2")
    public String test2(){
        return "/structured/test2";
    }

    @GetMapping("/test1")
    public String test3(){
        return "/unstructured/test1";
    }

    @GetMapping("/test4")
    public String test4() {return "/login/loginPage"; }
}
