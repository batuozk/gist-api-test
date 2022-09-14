package batuozk.gistapitest.test;

import batuozk.gistapitest.base.BaseTest;
import batuozk.gistapitest.base.ConfigReader;
import batuozk.gistapitest.base.Utilities;
import batuozk.gistapitest.requests.GetRequests;
import batuozk.gistapitest.requests.PostRequests;
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
        //TODO per_page & page parameters
        GetRequests getRequest = new GetRequests();
        String sinceDate = Utilities.getDateWithOffset(3);

        ValidatableResponse response = getRequest.getUserGists(sinceDate);
        ArrayList arrayList = response.extract().path("");

        for(int i = 0; i < arrayList.size(); i++){
            System.out.println("Gist " + (i+1) + "--------");
            var fileName = ((LinkedHashMap)((LinkedHashMap)arrayList.get(i)).get("files")).keySet().toArray()[0];
            System.out.println(fileName);
            System.out.println(((LinkedHashMap)((LinkedHashMap)arrayList.get(i)).get("owner")).get("login"));
        }
    }

    @Test
    public void createGist() {
        PostRequests postRequest = new PostRequests();
        String gistDesc = "Description of a test Gist - " + createGUID();
        String gistTitle = "TestGistTitle-" + createGUID() + ".txt";
        String gistContent = "Content of a test Gist - " + createGUID();
        boolean isPublic = false;

        ValidatableResponse response = postRequest.postGist(gistDesc, gistTitle, gistContent, isPublic);

        System.out.println("New Gist URL: " + response.extract().path("url"));
    }


    String createGUID(){
        return java.util.UUID.randomUUID().toString();
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

//              then
//				.statusCode(200)
//				.body("Result", equalTo(true))
//				.and().time(lessThan(10000L))
//              .extract().response().prettyPrint();