package batuozk.gistapitest.requests;

import batuozk.gistapitest.base.BaseApi;
import batuozk.gistapitest.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetRequests extends BaseApi {

    public ValidatableResponse getUserGists(String sinceDate){
        return requestSpec()
                .queryParam("since", sinceDate)
                .get()
                .then();
    }

    public ValidatableResponse getGistWithId(String gistId){
        return requestSpec()
                .get("/"+gistId)
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
}
