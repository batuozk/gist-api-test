package batuozk.gistapitest.requests;

import batuozk.gistapitest.base.BaseApi;
import batuozk.gistapitest.pojo.GistBody;
import com.google.gson.Gson;
import io.restassured.response.ValidatableResponse;
public class PostRequests extends BaseApi {
    public ValidatableResponse postGist(GistBody gistBody){
        return requestSpec()
                .body(new Gson().toJson(gistBody))
                .post()
                .then();
    }
}
