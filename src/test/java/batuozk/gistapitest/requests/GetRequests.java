package batuozk.gistapitest.requests;

import batuozk.gistapitest.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetRequests {

    public GetRequests(){
        RestAssured.baseURI = ConfigReader.getProperty("baseURI");
        RestAssured.basePath = ConfigReader.getProperty("basePath");
    }

    public ValidatableResponse getUserGists(String sinceDate){
        return requestSpec()
                .queryParam("since", sinceDate)
                .get()
                .then();
    }

    public ValidatableResponse getUserPublicGists(){
        return requestSpec()
                .get("/public")
                .then();
    }

    public ValidatableResponse getUserStarredGists(){
        return requestSpec()
                .get("/starred")
                .then();
    }

    private RequestSpecification requestSpec(){
        return given().auth()
                .oauth2(ConfigReader.getProperty("oauth2"))
                .header("accept", "application/vnd.github+json");
    }
}
