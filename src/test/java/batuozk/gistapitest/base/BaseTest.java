package batuozk.gistapitest.base;

import batuozk.gistapitest.mock.WireMockUtil;
import batuozk.gistapitest.requests.DeleteRequests;
import batuozk.gistapitest.requests.GetRequests;
import batuozk.gistapitest.requests.PatchRequests;
import batuozk.gistapitest.requests.PostRequests;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * Before, After, Logging, Setup, TearDown
 */
public class BaseTest {
    protected static GetRequests getRequest;
    protected static PostRequests postRequest;
    protected static PatchRequests patchRequests;
    protected static DeleteRequests deleteRequests;
    protected static WireMockUtil wireMockUtil;

    @BeforeAll
    public static void setUp(){
        System.out.println(ConfigReader.getProperty("baseURI") + ConfigReader.getProperty("basePath"));
        getRequest = new GetRequests();
        postRequest = new PostRequests();
        patchRequests = new PatchRequests();
        deleteRequests = new DeleteRequests();
        wireMockUtil = new WireMockUtil();
        System.out.println("Wiremock init");
        wireMockUtil.setup();
    }

    @AfterAll
    public static void endTest(){
        wireMockUtil.tearDown();
        System.out.println("End of Test");
    }



}
