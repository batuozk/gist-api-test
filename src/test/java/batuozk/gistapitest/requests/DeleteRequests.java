package batuozk.gistapitest.requests;

import batuozk.gistapitest.base.BaseApi;
import io.restassured.response.ValidatableResponse;

public class DeleteRequests extends BaseApi {
    public ValidatableResponse deleteGistById(String gistId){
        return requestSpec()
                .delete("/"+gistId)
                .then();
    }

}
