package batuozk.gistapitest.requests;

import batuozk.gistapitest.base.BaseApi;
import batuozk.gistapitest.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PostRequests extends BaseApi {

    public ValidatableResponse postGist(String gistDesc, String gistTitle, String gistContent, Boolean isPublic){
        String postBody = "{\"description\":\"" + gistDesc + "\","
                            + "\"public\":" + isPublic + ","
                            + "\"files\":{"
                                + "\"" + gistTitle + "\":{"
                                    + "\"content\":" + "\"" + gistContent + "\""
                                    + "}"
                                + "}"
                            + "}";

        System.out.println(postBody);

        return requestSpec()
                .body(postBody)
                .post()
                .then();
    }
}
