package batuozk.gistapitest.mock;

import batuozk.gistapitest.base.ConfigReader;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.response.Response;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WireMockUtil {

    WireMockServer wireMockServer;
    int portNumber = Integer.parseInt(ConfigReader.getProperty("wireMockPort"));

    public void setup(){
        wireMockServer = new WireMockServer(portNumber);
        wireMockServer.start();
        wireMockServer.stubFor(get(urlEqualTo("/testUrl"))
                    .willReturn(aResponse().withHeader("Content-Type", "application/json")
                    .withStatus(200)
                    .withBodyFile("gistToUpdate.json")));
    }

    public String getGistBodyData() {
        Response response = given().
                            when().
                            get("http://localhost:"+portNumber+ "/testUrl");
        return response.prettyPrint();
    }

    public void tearDown(){
        wireMockServer.stop();
    }

}
