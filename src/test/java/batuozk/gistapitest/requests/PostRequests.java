package batuozk.gistapitest.requests;

import batuozk.gistapitest.base.BaseApi;
import batuozk.gistapitest.pojo.GistBody;
import com.google.gson.Gson;
import io.restassured.response.Response;
public class PostRequests extends BaseApi {
    public Response postGist(GistBody gistBody){
        return requestSpec()
                .body(new Gson().toJson(gistBody))
                .post();
    }
}
