package batuozk.gistapitest.test;

import batuozk.gistapitest.base.BaseTest;
import batuozk.gistapitest.base.ConfigReader;
import batuozk.gistapitest.base.Utilities;
import batuozk.gistapitest.requests.GetRequests;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class SmokeTests extends BaseTest {
    @Test
    public void fetchGist() {
        GetRequests getRequest = new GetRequests();
        String sinceDate = Utilities.getDateWithOffset(3);

        var response = getRequest.getUserGists(sinceDate);
        ArrayList arrayList = response.extract().path("");

        for(int i = 0; i < arrayList.size(); i++){
            System.out.println("Gist " + i + " --------");
            var fileName = ((LinkedHashMap)((LinkedHashMap)arrayList.get(i)).get("files")).keySet().toArray()[0];
            System.out.println(fileName);
            System.out.println(((LinkedHashMap)((LinkedHashMap)arrayList.get(i)).get("owner")).get("login"));
        }


    }
}
//        ArrayList arrayList = response.extract().path("");
//        for(var gistData : arrayList){
//                ((LinkedHashMap)gistData).get("files").keySet().get(0)
//                LinkedHashMap hashmap = response.extract().path("[1].files");
//                Set<String> keys = hashmap.keySet();
//        System.out.println(hashmap.toString());
//        System.out.println(hashmap);
//        }

//        response.extract().
//            .statusCode(200)
//            .body("ResponseState",equalTo(0))
//            .and().time(lessThan(10000L))
//            .extract().response().prettyPrint();