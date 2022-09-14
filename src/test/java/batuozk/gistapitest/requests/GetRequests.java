package batuozk.gistapitest.requests;

import batuozk.gistapitest.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class GetRequests {

    public GetRequests(){
        System.out.println(ConfigReader.getProperty("baseURI") + ConfigReader.getProperty("basePath"));
        RestAssured.baseURI = ConfigReader.getProperty("baseURI");
        RestAssured.basePath = ConfigReader.getProperty("basePath");
    }

    public ValidatableResponse getUserGists(String sinceDate){
        return given().auth()
                .oauth2(ConfigReader.getProperty("oauth2"))
                .queryParam("since", sinceDate)
                .header("accept", "application/vnd.github+json")
                .get()
                .then();
    }
}
