package batuozk.gistapitest.mock;

import batuozk.gistapitest.base.ConfigReader;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WireMockUtil {

    WireMockServer wireMockServer;
    int portNumber = Integer.parseInt(ConfigReader.getProperty("wireMockPort"));
    Map<String, String> stubConfigList = new HashMap<>();

    public WireMockUtil(){
        wireMockServer = new WireMockServer(portNumber);
        wireMockServer.start();
        stubConfigList.put("/gistToUpdate", "gistToUpdate.json");
        stubConfigList.put("/gistToCreate", "gistToCreate.json");
        initializeStubs(stubConfigList);
    }

    public String getNewGistRequestBody() {
        Response response = given().
                            when().
                            get("http://localhost:"+portNumber+ "/gistToCreate");
        return response.body().asString();
    }

    public String getGistUpdateRequestBody() {
        Response response = given().
                            when().
                            get("http://localhost:"+portNumber+ "/gistToUpdate");
        return response.body().asString();
    }

    public void tearDown(){
        wireMockServer.stop();
    }

    /**
     * Creates a Get stub for each mock API configuration
     * @param stubConfigList [Url, filename] type map
     */
    private void initializeStubs(Map<String, String> stubConfigList){
        for(var stubConfig : stubConfigList.entrySet()){
            wireMockServer.stubFor(get(urlEqualTo(stubConfig.getKey()))
                    .willReturn(aResponse().withHeader("Content-Type", "application/json")
                            .withStatus(200)
                            .withBodyFile(stubConfig.getValue())));
        }
    }
}
