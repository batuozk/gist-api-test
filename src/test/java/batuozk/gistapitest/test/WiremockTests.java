package batuozk.gistapitest.test;

import batuozk.gistapitest.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

public class WiremockTests extends BaseTest {
    /**
     * Wiremock connection test (because other tests require oauth key to be stored in the repo)
     */
    @Test
    public void wireMockConnectionTest(){
        String wiremockBody = wireMockUtil.getNewGistRequestBody();
        try {
            File myFile = new File("src/test/resources/__files/gistToCreate.json");
            Assertions.assertEquals(Files.readString(myFile.toPath()), wiremockBody, "equal is no");
            System.out.println("JSON body from file reader matches wiremock body.");
        } catch (FileNotFoundException e) {
            Assertions.fail("Cannot find gistToCreate.json\n" + e);
        } catch (IOException e) {
            Assertions.fail("Cannot read gistToCreate.json\n" + e);
        }
    }
}
