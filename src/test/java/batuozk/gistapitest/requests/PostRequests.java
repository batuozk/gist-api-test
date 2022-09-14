package batuozk.gistapitest.requests;

import batuozk.gistapitest.base.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class PostRequests {

    public PostRequests(){
        RestAssured.baseURI = ConfigReader.getProperty("baseURI");
        RestAssured.basePath = ConfigReader.getProperty("basePath");
    }

    public ValidatableResponse postGist(String gistDesc, String gistTitle, String gistContent, boolean isPublic){
        String postBody = "{\"description\":\"" + gistDesc + "\","
                            + "\"public\":" + isPublic + ","
                            + "\"files\":{"
                                + "\"" + gistTitle + "\":{"
                                    + "\"content\":" + "\"" + gistContent + "\""
                                    + "}"
                                + "}"
                            + "}";

        System.out.println(postBody);

        return given().auth()
                .oauth2(ConfigReader.getProperty("oauth2"))
                .header("accept", "application/vnd.github+json")
                .body(postBody)
                .post()
                .then();
    }

}
