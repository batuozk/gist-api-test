package batuozk.gistapitest.requests;

import batuozk.gistapitest.base.BaseApi;
import io.restassured.response.ValidatableResponse;

public class PatchRequests extends BaseApi {

    public ValidatableResponse updateGist(String description,
                                          Boolean isPublic,
                                          String fileKey,
                                          String contentDesc,
                                          String gistId){
        String postBody =
                "{\"description\":\"" + description + "\","
                + "\"files\":{"
                    + "\"" + fileKey + "\":{"
                        + "\"content\":" + "\"" + contentDesc + "\""
                        + "}"
                    + "}"
                + "}";

        return requestSpec()
                .body(postBody)
                .patch("/"+gistId)
                .then();
    }

}
