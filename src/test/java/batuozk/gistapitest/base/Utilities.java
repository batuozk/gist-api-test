package batuozk.gistapitest.base;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Utilities {

    /**
     * Returns ISO-8601 type date value as String
     * @param months Number of months to substract from '<code>now</code>'
     * @return ISO-8601 date String
     */
    public static String getDateWithOffset(int months){
        return ZonedDateTime
                .now(ZoneId.of("Asia/Istanbul"))
                .minusMonths(months)
                .format( DateTimeFormatter.ISO_INSTANT );
    }
    /**
     * Creates a universally unique identifier
     * @return UUID string
     */
    public static String createUUID(){
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * Prints each gist's filename & gist owner
     * @param arrayList Response body as a list
     */
    public static void printUserGists(ArrayList arrayList){
        for(int i = 0; i < arrayList.size(); i++){
            var fileName = ((LinkedHashMap)((LinkedHashMap)arrayList.get(i)).get("files")).keySet().toArray()[0];
            System.out.println("Gist " + (i+1) + ": " + fileName);
//            System.out.println(((LinkedHashMap)((LinkedHashMap)arrayList.get(i)).get("owner")).get("login"));
        }
    }

    public static void assertStatusCode(Response response, int expectedStatusCode){
        int statusCode = response.statusCode();
        Assertions.assertEquals(expectedStatusCode, statusCode, "Bad status code, very bad.");
    }
}
