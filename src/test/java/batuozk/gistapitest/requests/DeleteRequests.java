package batuozk.gistapitest.requests;

import batuozk.gistapitest.base.BaseApi;
import io.restassured.response.Response;

public class DeleteRequests extends BaseApi {
    public Response deleteGistById(String gistId){
        return requestSpec()
                .delete("/"+gistId);
    }

}
