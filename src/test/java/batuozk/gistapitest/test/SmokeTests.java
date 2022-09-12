package batuozk.gistapitest.test;

import batuozk.gistapitest.base.BaseTest;
import batuozk.gistapitest.base.ConfigReader;
import batuozk.gistapitest.base.Utilities;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class SmokeTests extends BaseTest {
    @Test
    public void fetchGist() {
        String sinceDate = Utilities.getDateWithOffset(3);
        System.out.println(sinceDate);

        ValidatableResponse response = given().auth()
            .oauth2(ConfigReader.getProperty("oauth2"))
            .queryParam("since","2020-01-01T00:00:00Z" )
            .header("accept", "application/vnd.github+json")
            .when()
            .get()
            .then();//.extract().response().prettyPrint();

        response
//            .statusCode(200)
//            .body("ResponseState",equalTo(0))
//            .and().time(lessThan(10000L))
            .extract().response().prettyPrint();
    }
}
