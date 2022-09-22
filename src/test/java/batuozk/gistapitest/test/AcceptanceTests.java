package batuozk.gistapitest.test;

import batuozk.gistapitest.base.BaseTest;
import org.junit.jupiter.api.Test;

public class AcceptanceTests extends BaseTest {

    SmokeTests smokeTest = new SmokeTests();

    @Test
    public void acceptanceScenario(){
        smokeTest.listGist();
        smokeTest.getGistWithId();
        smokeTest.listStarredGists();
        smokeTest.listPublicGists();
        smokeTest.createGist();
        smokeTest.updateGist();
        smokeTest.deleteGist();
    }

}
