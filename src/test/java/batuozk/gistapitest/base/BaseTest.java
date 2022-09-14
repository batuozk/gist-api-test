package batuozk.gistapitest.base;

import batuozk.gistapitest.requests.GetRequests;
import batuozk.gistapitest.requests.PostRequests;
import org.junit.jupiter.api.BeforeAll;

/**
 * Before, After, Logging, Setup, TearDown
 */
public class BaseTest {
    protected static GetRequests getRequest;
    protected static PostRequests postRequest;

    @BeforeAll
    public static void setUp(){
        System.out.println(ConfigReader.getProperty("baseURI") + ConfigReader.getProperty("basePath"));
        getRequest = new GetRequests();
        postRequest = new PostRequests();
    }



}
