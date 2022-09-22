package batuozk.gistapitest.test;

import batuozk.gistapitest.base.BaseTest;
import batuozk.gistapitest.base.ConfigReader;
import batuozk.gistapitest.base.Utilities;
import batuozk.gistapitest.pojo.GistBody;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;


public class SmokeTests extends BaseTest {

    //TODO per_page & page parameters
    //BBB status code kontrolü 200 yerine 401 geldiği zaman bunun sebebi Assertion ile detaylandırılmalı mı?
    //BBB createdGist()'te yaratılan Gist'ler için bir temizleme protokolü yapılsa nereye koyulur?

    /**
     * Gets authenticated users gists and prints them on the console.
     * Fails test if status code is not 200 or returned gist count is less than 1.
     */
    @Test
    public void listGist() {
        var response = getRequest.getUserGists();

        ArrayList arrayList = response.path("");
        Assertions.assertTrue(arrayList.size() > 0);
        Utilities.assertStatusCode(response, 200);
        Utilities.printUserGists(arrayList);
    }

    /**
     * Creates a new gist for the authenticated user. Prints out the created gists URL
     *
     */
    @Test
    public void createGist() {
        String gistBody = wireMockUtil.getGistBodyData("/testUrl").body().toString();
        Response response = postRequest.postGist(gistBody);
        Utilities.assertStatusCode(response, 201);

        String gistUrl = response.path("url");
        System.out.println("New Gist URL: " + gistUrl);
    }

    /**
     * Gets public gists listed on GitHub
     */
    @Test
    public void listPublicGists(){
        Response response = getRequest.getUserPublicGists();
        ArrayList arrayList = response.path("");

        Assertions.assertTrue(arrayList.size() > 0);
        Utilities.assertStatusCode(response, 200);
        Utilities.printUserGists(arrayList);
    }

    @Test
    public void listStarredGists(){
        Response response = getRequest.getUserStarredGists();
        ArrayList arrayList = response.path("");

        Assertions.assertTrue(arrayList.size() > 0);
        Utilities.assertStatusCode(response, 200);
        Utilities.printUserGists(arrayList);
    }

    @Test
    public void getGistWithId(){
        String gistId = ConfigReader.getProperty("gistById");
        System.out.println("Gist with ID: " + gistId);
        Response response = getRequest.getGistWithId(gistId);

        Utilities.assertStatusCode(response, 200);
        System.out.println(response.path("description").toString());
    }

    @Test
    public void updateGist(){
        String gistId = ConfigReader.getProperty("gistToUpdate");
        String updatedDescription = "Description - " + Utilities.createUUID();
        GistBody gistBody = new GistBody(
                updatedDescription,
                false,
                "gsonFilename.txt",
                "Content of the updated gist. Lorem ipsum dolor sit amet.");
        Response response = patchRequests.updateGist(gistBody, gistId);
        Utilities.assertStatusCode(response, 200);
        response.prettyPrint();
    }

    @Test
    public void deleteGist(){
        GistBody gistBody = new GistBody(
                "Gist to be deleted",
                false,
                "TestGistToDelete.txt",
                "Content of a test Gist to be deleted");
        Response createResponse = postRequest.postGist(gistBody);
        String gistId = createResponse.path("id");
        System.out.println("Gist ID to delete: " + gistId);

        Response deleteResponse = deleteRequests.deleteGistById(gistId);
        Utilities.assertStatusCode(deleteResponse,204);
        System.out.println("Gist deleted: " + gistId);
    }
}

//        GistBody gistBody = new GistBody(
//                "Description of a test Gist - " + Utilities.createUUID(),
//                false,
//                "TestGistTitle-" + Utilities.createUUID() + ".txt",
//                "Content of a test Gist - " + Utilities.createUUID());

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