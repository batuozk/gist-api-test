package batuozk.gistapitest.requests;

import batuozk.gistapitest.base.BaseApi;
import batuozk.gistapitest.pojo.GistBody;
import com.google.gson.Gson;
import io.restassured.response.Response;

public class PatchRequests extends BaseApi {
    public Response updateGist(GistBody gistBody, String gistId){
        Gson gson = new Gson();
        System.out.println(gson.toJson(gistBody));
        return requestSpec()
                .body(gson.toJson(gistBody))
                .patch("/"+gistId);
    }

}
