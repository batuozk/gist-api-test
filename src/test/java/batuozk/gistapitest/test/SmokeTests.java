package batuozk.gistapitest.test;

import batuozk.gistapitest.base.BaseTest;
import batuozk.gistapitest.base.ConfigReader;
import batuozk.gistapitest.base.Utilities;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


public class SmokeTests extends BaseTest {

    //TODO per_page & page parameters

    @Test
    public void fetchGist() {
        String sinceDate = Utilities.getDateWithOffset(3);

        ValidatableResponse response = getRequest.getUserGists(sinceDate);
        ArrayList arrayList = response.extract().path("");

        Utilities.printUserGists(arrayList);
    }

    @Test
    public void createGist() {
        String gistDesc = "Description of a test Gist - " + Utilities.createUUID();
        String gistTitle = "TestGistTitle-" + Utilities.createUUID() + ".txt";
        String gistContent = "Content of a test Gist - " + Utilities.createUUID();
        boolean isPublic = false;

        ValidatableResponse response = postRequest.postGist(gistDesc, gistTitle, gistContent, isPublic);
        System.out.println("New Gist URL: " + response.extract().path("url"));
    }

    @Test
    public void listPublicGists(){
        ValidatableResponse response = getRequest.getUserPublicGists();
        ArrayList arrayList = response.extract().path("");

        Utilities.printUserGists(arrayList);
    }

    @Test
    public void listStarredGists(){
        ValidatableResponse response = getRequest.getUserStarredGists();
        ArrayList arrayList = response.extract().path("");

        Utilities.printUserGists(arrayList);
    }

    @Test
    public void getGistWithId(){
        String gistId = ConfigReader.getProperty("gistById");
        System.out.println("Gist with ID: " + gistId);
        ValidatableResponse response = getRequest.getGistWithId(gistId);
        System.out.println(response.extract().path("description").toString());
    }

    @Test
    public void updateGist(){
        String gistId = ConfigReader.getProperty("gistToUpdate");
//        GistBody gistBody = new GistBody(
//                "Description of an updated gist",
//                false,
//                "filename.txt",
//                "Content of the updated gist. Lorem ipsum dolor sit amet.");
        ValidatableResponse response = patchRequests.updateGist(
                "Description of an updated gist",
                false,
                "updatedFilename.txt",
                "Content of the updated gist. Lorem ipsum dolor sit amet.",
                gistId);

//        response.extract().response().prettyPrint();
    }

    @Test
    public void deleteGist(){
        String gistDesc = "Gist to be deleted";
        String gistTitle = "TestGistToDelete.txt";
        String gistContent = "Content of a test Gist to be deleted";
        boolean isPublic = false;

        ValidatableResponse createResponse = postRequest.postGist(gistDesc, gistTitle, gistContent, isPublic);
        String gistId = createResponse.extract().path("id");
        System.out.println("Gist ID to delete: " + gistId);

        ValidatableResponse deleteResponse = deleteRequests.deleteGistById(gistId);
        deleteResponse.statusCode(204).extract().response().prettyPrint();
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