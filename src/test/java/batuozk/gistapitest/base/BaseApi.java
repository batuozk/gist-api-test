package batuozk.gistapitest.base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApi {

    public BaseApi(){
        RestAssured.baseURI = ConfigReader.getProperty("baseURI");
        RestAssured.basePath = ConfigReader.getProperty("basePath");
    }

    protected RequestSpecification requestSpec(){
        //TODO Header to be declared at BaseApi() constructor
        return given().auth()
                .oauth2(ConfigReader.getProperty("oauth2"))
                .header("accept", "application/vnd.github+json");
    }

}
