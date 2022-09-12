package batuozk.gistapitest.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

/**
 * Before, After, Logging, Setup, TearDown
 */
public class BaseTest {

    @BeforeAll
    public static void setUp(){
        System.out.println(ConfigReader.getProperty("baseURI") + ConfigReader.getProperty("basePath"));
        RestAssured.baseURI = ConfigReader.getProperty("baseURI");
        RestAssured.basePath = ConfigReader.getProperty("basePath");
    }

}
