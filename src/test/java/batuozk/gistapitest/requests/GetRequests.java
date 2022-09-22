package batuozk.gistapitest.requests;

import batuozk.gistapitest.base.BaseApi;
import io.restassured.response.Response;

public class GetRequests extends BaseApi {

    public Response getUserGists(){
        return requestSpec()
                .get();
    }

    public Response getGistWithId(String gistId){
        return requestSpec()
                .get("/"+gistId);
    }

    public Response getUserPublicGists(){
        return requestSpec()
                .get("/public");
    }

    public Response getUserStarredGists(){
        return requestSpec()
                .get("/starred");
    }
}
