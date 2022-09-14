package batuozk.gistapitest.test;

import batuozk.gistapitest.base.BaseTest;
import batuozk.gistapitest.base.Utilities;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class SmokeTests extends BaseTest {

    //TODO per_page & page parameters

    @Test
    public void fetchGist() {
        String sinceDate = Utilities.getDateWithOffset(3);

        ValidatableResponse response = getRequest.getUserGists(sinceDate);
        ArrayList arrayList = response.extract().path("");

        printUserGists(arrayList);
    }

    @Test
    public void createGist() {
        String gistDesc = "Description of a test Gist - " + createUUID();
        String gistTitle = "TestGistTitle-" + createUUID() + ".txt";
        String gistContent = "Content of a test Gist - " + createUUID();
        boolean isPublic = false;

        ValidatableResponse response = postRequest.postGist(gistDesc, gistTitle, gistContent, isPublic);
        System.out.println("New Gist URL: " + response.extract().path("url"));
    }

    @Test
    public void listPublicGists(){
        ValidatableResponse response = getRequest.getUserPublicGists();
        ArrayList arrayList = response.extract().path("");

        printUserGists(arrayList);
    }

    @Test
    public void listStarredGists(){
        ValidatableResponse response = getRequest.getUserStarredGists();
        ArrayList arrayList = response.extract().path("");

        printUserGists(arrayList);
    }

    /**
     * Creates a universally unique identifier
     * @return UUID string
     */
    String createUUID(){
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * Prints each gist's filename & gist owner
     * @param arrayList Response body as a list
     */
    void printUserGists(ArrayList arrayList){
        for(int i = 0; i < arrayList.size(); i++){
            System.out.println("Gist " + (i+1) + "--------");
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

//              then
//				.statusCode(200)
//				.body("Result", equalTo(true))
//				.and().time(lessThan(10000L))
//              .extract().response().prettyPrint();